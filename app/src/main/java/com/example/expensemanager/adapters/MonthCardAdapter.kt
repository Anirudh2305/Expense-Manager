package com.example.expensemanager.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.DiffCallback
import com.example.expensemanager.R
import com.example.expensemanager.data.CalendarMonth
import com.example.expensemanager.data.PlusMinus
import com.example.expensemanager.data.Transaction
import com.google.android.material.card.MaterialCardView
import kotlinx.android.extensions.LayoutContainer

class MonthCardAdapter(private val listener:(Int)->Unit) : RecyclerView.Adapter<MonthCardAdapter.ViewHolder>(){

    var monthList =arrayOf<Int>()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),LayoutContainer{

        init {
            itemView.setOnClickListener {
                listener.invoke(monthList[adapterPosition])
            }
        }

        private val monthName=containerView.findViewById<TextView>(R.id.month_card_text)
        private val card=containerView.findViewById<MaterialCardView>(R.id.cards_of_months)
        fun bind(month:Int){
                monthName.text=CalendarMonth.values()[month].toString()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthCardAdapter.ViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.month_cards, parent, false)

        return ViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: MonthCardAdapter.ViewHolder, position: Int) {
        holder.bind(monthList[position])
    }

    override fun getItemCount(): Int = monthList.size

}
/*
class MonthCardAdapter :ListAdapter<Transaction,MonthCardAdapter.ViewHolder>(DiffCallback()){

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),LayoutContainer{

        private val monthName=containerView.findViewById<TextView>(R.id.month_card_text)
        private val card=containerView.findViewById<MaterialCardView>(R.id.cards_of_months)
        fun bind(transaction: Transaction){
            with(transaction){
                monthName.text=CalendarMonth.values()[month].toString()
                /*
                when(cashFlow){
                    PlusMinus.Income.ordinal-> card.strokeColor= Color.parseColor("#66BB6A")
                    else-> card.strokeColor=Color.parseColor("#EF5350")
                }
                */
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.month_cards, parent, false)

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

 */