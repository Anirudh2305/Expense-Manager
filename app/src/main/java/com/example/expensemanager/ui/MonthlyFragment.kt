package com.example.expensemanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.R
import com.example.expensemanager.TransactionAdapter
import com.example.expensemanager.adapters.MonthCardAdapter
import com.example.expensemanager.data.AllMonthsViewModel
import com.example.expensemanager.data.ListViewModel
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.android.synthetic.main.fragment_monthly.*


class MonthlyFragment : Fragment() {

    private lateinit var viewModel: AllMonthsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProviders.of(this).get(AllMonthsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monthly, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(month_recyclerview){
            layoutManager=LinearLayoutManager(activity)
            adapter=MonthCardAdapter{
                findNavController().navigate(
                    FrontScreenFragmentDirections.actionFrontScreenFragmentToMonthWiseFragment(it)
                )
            }
        }

        viewModel.listOfMonths.observe(viewLifecycleOwner, Observer {
            (month_recyclerview.adapter as MonthCardAdapter).monthList=it
        })
    }

}