package com.example.expensemanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expensemanager.R
import com.example.expensemanager.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_front_screen.*


class FrontScreenFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_front_screen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setUpTabs()
        super.onActivityCreated(savedInstanceState)
    }

    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(HomeScreenFragment(),"ALL")
        adapter.addFragment(MonthlyFragment(),"MONTHLY")
        viewPager.adapter=adapter
        tabs.setupWithViewPager(viewPager)
    }

}