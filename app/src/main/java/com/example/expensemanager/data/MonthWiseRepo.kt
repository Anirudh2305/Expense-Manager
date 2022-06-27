package com.example.expensemanager.data

import android.app.Application
import androidx.lifecycle.LiveData

class MonthWiseRepo(context:Application) {

    private val monthWiseDao:MonthWiseDao = TransactionDatabase.getDatabase(context).monthWiseDao()

    fun getMonthTransactions(month:Int):LiveData<List<Transaction>>{
        return monthWiseDao.loadMonthTransactions(month)
    }
}