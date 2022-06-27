package com.example.expensemanager.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface MonthWiseDao {
    @Query("SELECT*FROM `transaction` where month=:month")
    fun loadMonthTransactions(month:Int):LiveData<List<Transaction>>
}