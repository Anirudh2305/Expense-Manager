package com.example.expensemanager.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData

class AllMonthsRepo(context: Application) {

    private val allMonthsDao:AllMonthsDao = TransactionDatabase.getDatabase(context).allMonthsDao()

    fun getCards():LiveData<Array<Int>>{
        return allMonthsDao.loadMonthCards()
    }
}