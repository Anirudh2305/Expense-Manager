package com.example.expensemanager.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class MonthWiseViewModel(application: Application):AndroidViewModel(application) {

    private val repo:MonthWiseRepo = MonthWiseRepo(application)
    private val _month = MutableLiveData<Int>()

    val monthlyTransactions:LiveData<List<Transaction>> = Transformations.switchMap(_month){
        repo.getMonthTransactions(it)
    }

    fun setMonth(month:Int){
        _month.value=month
    }
}