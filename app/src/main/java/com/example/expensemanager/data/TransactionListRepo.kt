package com.example.expensemanager.data

import android.app.Application
import androidx.lifecycle.LiveData

class TransactionListRepo(context:Application) {

    private val transactionListDao:TransactionListDao = TransactionDatabase.getDatabase(context).transactionListDao()

    fun getTransactions():LiveData<List<Transaction>>{
        return transactionListDao.loadAllTransactions()
    }


}