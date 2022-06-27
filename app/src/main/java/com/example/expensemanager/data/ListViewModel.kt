package com.example.expensemanager.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ListViewModel(application: Application):AndroidViewModel(application) {

    private val repo:TransactionListRepo = TransactionListRepo(application)

    val transactions:LiveData<List<Transaction>> = repo.getTransactions()

}