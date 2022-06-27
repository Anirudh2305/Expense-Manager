package com.example.expensemanager.data

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class DetailViewModel(application: Application):AndroidViewModel(application) {

    private val repo:TransactionDetailRepo=TransactionDetailRepo(application)

    private val _transactionId = MutableLiveData<Long>(0)

    val transactionId:LiveData<Long>
    get()=_transactionId

    val transaction:LiveData<Transaction> = Transformations.switchMap(_transactionId){
        repo.getTransaction(it)
    }

    fun setTransactionId(id:Long){           //_transacId holds latest ins/updated/del task's id. If we click a different task
                                             // then _transacId value needs to be set to that task(transaction).
        if(_transactionId.value!=id)
            _transactionId.value=id
    }

    fun saveTransaction(transaction: Transaction){
        viewModelScope.launch {
            if(_transactionId.value==0L)
                repo.insertTransaction(transaction)
            else
                repo.updateTransaction(transaction)
        }
    }

    fun deleteTransaction(){
        viewModelScope.launch {
            transaction.value?.let { repo.deleteTransaction(it)}
        }
    }
}