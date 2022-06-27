package com.example.expensemanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class CalendarMonth{
    January,February,March,April,May,June,July,August,September,October,November,December
}

enum class TypeTransaction{
    Cash,Cheque,Credit
}

enum class PlusMinus{
    Expense, Income
}

@Entity(tableName = "transaction")
data class Transaction(@PrimaryKey(autoGenerate = true) val id:Long, val name:String, val date:String,
                       val amount:String, val type:Int, val month:Int, val cashFlow:Int, val cost:Float)
