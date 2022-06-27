package com.example.expensemanager.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.R
import com.example.expensemanager.TransactionAdapter
import com.example.expensemanager.data.ListViewModel
import kotlinx.android.synthetic.main.custom_prog_bar.*
import kotlinx.android.synthetic.main.fragment_home_screen.*
import org.eazegraph.lib.models.PieModel

class HomeScreenFragment : Fragment() {

    private lateinit var viewModel:ListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(all_transactions){
            layoutManager=LinearLayoutManager(activity)
            adapter= TransactionAdapter{
                findNavController().navigate(
                    //HomeScreenFragmentDirections.actionHomeScreenFragmentToAddTransactionFragment(it)
                FrontScreenFragmentDirections.actionFrontScreenFragmentToAddTransactionFragment(it)
                )
            }
        }

        add_transaction.setOnClickListener {
          findNavController().navigate(
              //HomeScreenFragmentDirections.actionHomeScreenFragmentToAddTransactionFragment(0)
          FrontScreenFragmentDirections.actionFrontScreenFragmentToAddTransactionFragment(0)
          )
        }

        viewModel.transactions.observe(viewLifecycleOwner, Observer {
            (all_transactions.adapter as TransactionAdapter).submitList(it)
        })

        setChart()

    }

    private fun setChart(){

        var mCash=5000F
        var mCheque=2000F
        var mCredit=3000F

        cash.text = getString(R.string.cash,mCash)
        cheque.text = getString(R.string.cheque,mCheque)
        credit.text = getString(R.string.credit,mCredit)
        Net_balance.text = getString(R.string.nb,mCash+mCheque+mCredit)

        // pieChart.addPieSlice(PieModel(cash.text.toString().toInt()))
        pieChart.addPieSlice(
            PieModel(
                "cash", mCash,
                Color.parseColor("#EF5350")
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "cheque", mCheque,
                Color.parseColor("#FF6200EE")
            )
        )

        pieChart.addPieSlice(
            PieModel(
                "credit", mCredit,
                Color.parseColor("#008000")
            )
        )

        // pieChart.startAnimation()  // could lead to frame skipping as main thread does too much work.
    }
}