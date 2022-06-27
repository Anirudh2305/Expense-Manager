package com.example.expensemanager.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class AllMonthsViewModel(application: Application):AndroidViewModel(application) {

    private val repo:AllMonthsRepo = AllMonthsRepo(application)
    val listOfMonths:LiveData<Array<Int>> = repo.getCards()
}