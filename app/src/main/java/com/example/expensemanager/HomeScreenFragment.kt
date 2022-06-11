package com.example.expensemanager

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.custom_prog_bar.*
import org.eazegraph.lib.models.PieModel

class HomeScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        cash.text = getString(R.string.cash,5000.0)
        cheque.text = getString(R.string.cheque,3000.0)
        credit.text = getString(R.string.credit,2000.0)
        Net_balance.text = getString(R.string.nb,10000.0)

       // pieChart.addPieSlice(PieModel(cash.text.toString().toInt()))
        pieChart.addPieSlice(
            PieModel(
                "cash", 5000F,
                Color.parseColor("#FF6200EE")
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "cheque", 3000F,
                Color.parseColor("#FF018786")
            )
        )

        pieChart.addPieSlice(
            PieModel(
                "credit", 2000F,
                Color.parseColor("#024265")
            )
        )

        pieChart.startAnimation()

    }


}