package com.example.expensemanager

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.data.PlusMinus
import com.example.expensemanager.data.Transaction
import com.example.expensemanager.data.TypeTransaction
import kotlinx.android.extensions.LayoutContainer

class TransactionAdapter(private val listener:(Long)->Unit):
    ListAdapter<Transaction,TransactionAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        init {
            itemView.setOnClickListener {
                listener.invoke(getItem(adapterPosition).id)
            }
        }

        private val itemName = containerView.findViewById<TextView>(R.id.item_name)
        private val itemDate = containerView.findViewById<TextView>(R.id.item_date)
        private val itemType = containerView.findViewById<TextView>(R.id.item_type)
        private val itemAmount = containerView.findViewById<TextView>(R.id.item_amount)

        fun bind(trade: Transaction){
            with(trade){
                itemName.text = name
                itemDate.text = date
               // itemType.text = type.toString()
                when(type){
                    TypeTransaction.Cash.ordinal-> itemType.text="\u2022 Cash"
                    TypeTransaction.Cheque.ordinal->itemType.text="\u2022 Cheque"
                    else->itemType.text="\u2022 Credit"
                }

                when(cashFlow){
                    PlusMinus.Income.ordinal-> {
                        itemAmount.text = "+$amount"
                        itemAmount.setTextColor(Color.parseColor("#66BB6A"))

                    }
                    else->{
                        itemAmount.text="-$amount"
                        itemAmount.setTextColor(Color.parseColor("#EF5350"))
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(itemLayout)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallback : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }
}