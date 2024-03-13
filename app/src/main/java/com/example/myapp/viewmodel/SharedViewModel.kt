package com.example.myapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapp.Transactions

class SharedViewModel : ViewModel() {
    val transactions = MutableLiveData<List<Transactions>>().apply {
        value = listOf(
            Transactions("Salary", "02/01/2024", 150000, "Income", "Job"),
            Transactions("Electronics", "02/01/2024", 7500, "Expense", "Transportation"),
            Transactions("Hotel", "06/01/2024", 4000, "Expense", "Food"),
            Transactions("Shopping", "08/01/2024", 600, "Expense", "Taxi"),
            Transactions("Bonus", "16/01/2024", 15000, "Income", "Freelance"),
            Transactions("Car", "25/01/2024", 5200, "Expense", "Food"),
            Transactions("Laptop", "26/01/2024", 73000, "Expense", "Food"),
            Transactions("Gift", "15/02/2024", 56000, "Income", "Bonus")
        )
    }

    fun addTransaction(transaction: Transactions) {
        val currentList = transactions.value.orEmpty().toMutableList()
        currentList.add(transaction)
        transactions.value = currentList
    }

    fun deleteTransaction(index: Int) {
        val currentList = transactions.value.orEmpty().toMutableList()
        if (index in 0 until currentList.size) {
            currentList.removeAt(index)
            transactions.value = currentList
        }
    }
}

