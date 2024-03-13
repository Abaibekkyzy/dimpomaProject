package com.example.myapp

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale


class TransactionAdapter(
    private var transactions: List<Transactions>,
    private val onDeleteClickListener: (Int) -> Unit
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headingTextView: TextView = itemView.findViewById(R.id.headingOfTransaction)
        val dateTextView: TextView = itemView.findViewById(R.id.dateOfTransaction)
        val amountTextView: TextView = itemView.findViewById(R.id.amountOfTransaction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val currentTransaction = transactions[position]

        holder.headingTextView.text = currentTransaction.heading
        holder.dateTextView.text = currentTransaction.date

        if (currentTransaction.type == "Expense") {
            holder.amountTextView.setTextColor(Color.RED)
            holder.amountTextView.text = "-${formatAmount(currentTransaction.amount)}"
        } else if (currentTransaction.type == "Income") {
            holder.amountTextView.setTextColor(Color.GREEN)
            holder.amountTextView.text = "+${formatAmount(currentTransaction.amount)}"
        }

        holder.itemView.setOnClickListener {
            onDeleteClickListener.invoke(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    fun setTransactions(newTransactions: List<Transactions>) {
        transactions = newTransactions.toMutableList()
        notifyDataSetChanged()
    }

    private fun formatAmount(price: Int): String {
        val currencySymbol = "₸"
        return "${price.toString().replace(Regex("(\\d)(?=(\\d{3})+\$)"), "$1 ")} $currencySymbol"
    }
}


