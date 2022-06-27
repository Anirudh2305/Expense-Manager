package com.example.expensemanager.ui

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.expensemanager.R
import com.example.expensemanager.data.DetailViewModel
import com.example.expensemanager.data.Transaction
import com.example.expensemanager.data.TypeTransaction
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_add_transaction.*
import java.text.SimpleDateFormat
import java.util.*

class AddTransactionFragment : Fragment() {

    private lateinit var viewModel:DetailViewModel
    private var month:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transaction_date_text.transformIntoDatePicker(requireContext(),"dd MMM yyyy")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val types = mutableListOf<String>()
        TypeTransaction.values().forEach { types.add(it.name) }
        val arrayAdapter = ArrayAdapter(activity!!,android.R.layout.simple_spinner_item,types)
        transaction_type.adapter=arrayAdapter


        val id = AddTransactionFragmentArgs.fromBundle(requireArguments()).id
        viewModel.setTransactionId(id)

        viewModel.transaction.observe(viewLifecycleOwner, Observer {
            it?.let { setData(it) }
        })

        expense_button.setOnClickListener {
            save(0)
        }

        income_button.setOnClickListener {
            save(1)
        }
    }

    private fun setData(transaction:Transaction){
        transaction_name_text.setText(transaction.name)
        transaction_date_text.setText(transaction.date)
        transaction_amount_text.setText(transaction.amount)
        transaction_type.setSelection(transaction.type)
    }

    private fun save(cashFlow:Int){

        val title=transaction_name_text.text.toString()
        val amount=transaction_amount_text.text.toString()
        //transaction_date_text.transformIntoDatePicker(requireContext(),"MM/dd/yyyy") called in onViewCreated()
        val date=transaction_date_text.text.toString()
        val type=transaction_type.selectedItemPosition

        val transaction = Transaction(viewModel.transactionId.value!!,title,date,amount,type,month,cashFlow,amount.toFloat())
        viewModel.saveTransaction(transaction)
        activity!!.onBackPressed()
    }

    private fun TextInputEditText.transformIntoDatePicker(context: Context, format: String, maxDate: Date? = null) {
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false

        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat(format, Locale.UK)
                setText(sdf.format(myCalendar.time))
                 month=monthOfYear
            }

        setOnClickListener {
            DatePickerDialog(
                context, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                maxDate?.time?.also { datePicker.maxDate = it }
                show()
            }
        }
    }

}