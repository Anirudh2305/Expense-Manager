package com.example.expensemanager.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expensemanager.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val fragment = OnBoarding()

        /*
        val fragment = FrontScreenFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.main_container,fragment,"OnBoarding")
        fragmentTransaction.commit()
        */


        //setUpTabs()

    }
    /*
    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeScreenFragment(),"ALL")
        adapter.addFragment(MonthlyFragment(),"MONTHLY")
        viewPager.adapter=adapter
        tabs.setupWithViewPager(viewPager)
    }

     */
}