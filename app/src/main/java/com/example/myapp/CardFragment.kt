package com.example.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.viewmodel.SharedViewModel

import android.app.AlertDialog

class CardFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewTransactions)
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = TransactionAdapter(sharedViewModel.transactions.value.orEmpty()) { position ->
            showDeleteConfirmationDialog(position)
        }

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        updateBalance(view)

        val buttonGoToAddTransaction: Button = view.findViewById(R.id.buttonAddTransaction)
        buttonGoToAddTransaction.setOnClickListener {
            navController.navigate(R.id.action_cardFragment_to_addTransactionFragment)
        }

        sharedViewModel.transactions.observe(viewLifecycleOwner) {
            adapter.setTransactions(it)
            updateBalance(view)
        }
    }

    private fun showDeleteConfirmationDialog(position: Int) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_alert_dialog, null)
        val alertDialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
        val alertDialog = alertDialogBuilder.create()

        val titleTextView: TextView = dialogView.findViewById(R.id.dialogTitle)
        val messageTextView: TextView = dialogView.findViewById(R.id.dialogMessage)
        val buttonYes: Button = dialogView.findViewById(R.id.buttonYes)
        val buttonNo: Button = dialogView.findViewById(R.id.buttonNo)

        titleTextView.text = "Delete Transaction"
        messageTextView.text = "Are you sure you want to delete this transaction?"

        buttonYes.setOnClickListener {
            sharedViewModel.deleteTransaction(position)
            alertDialog.dismiss()
        }

        buttonNo.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun updateBalance(view: View) {
        val balanceTextView: TextView = view.findViewById(R.id.textViewBalance)

        var balance = 0
        for (transaction in sharedViewModel.transactions.value.orEmpty()) {
            if (transaction.type == "Expense") {
                balance -= transaction.amount
            } else if (transaction.type == "Income") {
                balance += transaction.amount
            }
        }

        val formattedBalance = formatAmount(balance)
        balanceTextView.text = "${formattedBalance}"
    }

    private fun formatAmount(price: Int): String {
        val currencySymbol = "₸"
        return "${price.toString().replace(Regex("(\\d)(?=(\\d{3})+\$)"), "$1 ")} $currencySymbol"
    }
}




