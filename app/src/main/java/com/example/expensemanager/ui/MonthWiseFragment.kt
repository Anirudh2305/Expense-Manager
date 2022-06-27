package com.example.expensemanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.R
import com.example.expensemanager.TransactionAdapter
import com.example.expensemanager.data.MonthWiseViewModel
import kotlinx.android.synthetic.main.fragment_month_wise.*


class MonthWiseFragment : Fragment() {

    private lateinit var viewModel: MonthWiseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         viewModel=ViewModelProviders.of(this).get(MonthWiseViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_month_wise, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        monthlyProgress.progress=(6000*100/11000)
        monthly_save.text= getString(R.string.nb,6000F)
        monthly_spent.text=getString(R.string.nb,5000F)
        monthlyNb.text=getString(R.string.nb,11000F)

        val month = MonthWiseFragmentArgs.fromBundle(requireArguments()).month
        viewModel.setMonth(month)

        with(monthRv){
            layoutManager=LinearLayoutManager(activity)
            adapter=TransactionAdapter{
                findNavController().navigate(
                    MonthWiseFragmentDirections.actionMonthWiseFragmentToAddTransactionFragment(it)
                )
            }
        }

        viewModel.monthlyTransactions.observe(viewLifecycleOwner, Observer {
            (monthRv.adapter as TransactionAdapter).submitList(it)
        })

    }
}