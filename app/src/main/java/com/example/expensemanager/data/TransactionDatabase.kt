package com.example.expensemanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Transaction::class], version = 1)
abstract class TransactionDatabase : RoomDatabase(){

    abstract fun transactionListDao():TransactionListDao
    abstract fun transactionDetailDao():TransactionDetailDao
    abstract fun allMonthsDao():AllMonthsDao
    abstract fun monthWiseDao():MonthWiseDao

    companion object{
        @Volatile
        private var instance:TransactionDatabase?=null
        fun getDatabase(context:Context)= instance?: synchronized(this){
            Room.databaseBuilder(
                context.applicationContext,
                TransactionDatabase::class.java,"database_name").build().also { instance=it }
        }
    }
}