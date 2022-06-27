package com.example.expensemanager.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface TransactionListDao {
    @Query("Select * from `transaction` order by date")
    fun loadAllTransactions(): LiveData<List<Transaction>>

}