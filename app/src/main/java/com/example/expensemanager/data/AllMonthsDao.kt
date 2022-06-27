package com.example.expensemanager.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface AllMonthsDao {
    @Query("Select distinct month from `transaction` order by month")
    fun loadMonthCards(): LiveData<Array<Int>>
}