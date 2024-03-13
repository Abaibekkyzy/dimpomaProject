package com.example.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapp.viewmodel.SharedViewModel

class AddTransactionFragment : Fragment() {
    private lateinit var transactionTypeSpinner: Spinner
    private lateinit var categorySpinner: Spinner
    private lateinit var datePicker: DatePicker
    private lateinit var addButton: Button
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_transaction, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val navController = findNavController()

        transactionTypeSpinner = view.findViewById(R.id.spinnerTransactionType)
        categorySpinner = view.findViewById(R.id.spinnerTransactionCategory)

        val transactionTypeAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.transaction_types,
            android.R.layout.simple_spinner_item
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        transactionTypeSpinner.adapter = transactionTypeAdapter

        // Обработчик выбора для первого Spinner
        transactionTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateCategories(transactionTypeSpinner.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Обработка, если ничего не выбрано
            }
        }



        datePicker = view.findViewById(R.id.datePickerForTransaction)
        addButton = view.findViewById(R.id.buttonADD)

        addButton.setOnClickListener {
            onAddTransactionButtonClick(view)
        }

        val buttonBackOnAddPage: ImageView = view.findViewById(R.id.buttonBackOnAddPage)

        buttonBackOnAddPage.setOnClickListener {
            navController.navigate(R.id.action_addTransactionFragment_to_cardFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController.navigate(R.id.action_addTransactionFragment_to_cardFragment)
        }


        return view
    }

    private fun updateCategories(selectedTransactionType: String) {
        val categories: List<String> = when (selectedTransactionType) {
            "Income" -> resources.getStringArray(R.array.income_categories).toList()
            "Expense" -> resources.getStringArray(R.array.expense_categories).toList()
            else -> emptyList()
        }

        val categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = categoryAdapter
    }



    private fun onAddTransactionButtonClick(view: View) {
        val selectedType = transactionTypeSpinner.selectedItem.toString()
        val selectedCategory = categorySpinner.selectedItem.toString()
        val transactionName = view.findViewById<EditText>(R.id.editTextAddTransactionName).text.toString()
        val amountText = view.findViewById<EditText>(R.id.editViewAmountTranslation).text.toString()

        if (transactionName.isEmpty() || amountText.isEmpty() || selectedType.isEmpty() || !dateIsSelected()) {
            Toast.makeText(requireContext(), "Please fill in all the required fields", Toast.LENGTH_SHORT).show()
        } else {
            val amount = amountText.toInt()

            val day = datePicker.dayOfMonth
            val month = datePicker.month
            val year = datePicker.year

            sharedViewModel.addTransaction(Transactions(transactionName, "$day/${month + 1}/$year", amount, selectedType, selectedCategory))
            Toast.makeText(requireContext(), "Transaction added successfully!", Toast.LENGTH_SHORT).show()

            val navController = findNavController()
            navController.navigate(R.id.action_addTransactionFragment_to_cardFragment)

        }
    }

    private fun dateIsSelected(): Boolean {
        return datePicker.year != 0 || datePicker.month != 0 || datePicker.dayOfMonth != 0
    }
}


/*
class AddTransactionFragment : Fragment() {

    private lateinit var spinner: Spinner
    private lateinit var datePicker: DatePicker
    private lateinit var addButton: Button
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_transaction, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val navController = findNavController()

        val options = arrayOf("Income", "Expense")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner = view.findViewById(R.id.spinnerTransactionType)
        spinner.adapter = adapter

        datePicker = view.findViewById(R.id.datePickerForTransaction)
        addButton = view.findViewById(R.id.buttonADD)

        addButton.setOnClickListener {
            onAddTransactionButtonClick(view)
        }

        val buttonBackOnAddPage: ImageView = view.findViewById(R.id.buttonBackOnAddPage)

        buttonBackOnAddPage.setOnClickListener {
            navController.navigate(R.id.action_addTransactionFragment_to_cardFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController.navigate(R.id.action_addTransactionFragment_to_cardFragment)
        }

        return view
    }

    private fun onAddTransactionButtonClick(view: View) {
        val selectedType = spinner.selectedItem.toString()
        val transactionName = view.findViewById<EditText>(R.id.editTextAddTransactionName).text.toString()
        val amountText = view.findViewById<EditText>(R.id.editViewAmountTranslation).text.toString()

        if (transactionName.isEmpty() || amountText.isEmpty() || selectedType.isEmpty() || !dateIsSelected()) {
            Toast.makeText(requireContext(), "Please fill in all the required fields", Toast.LENGTH_SHORT).show()
        } else {
            val amount = amountText.toInt()

            val day = datePicker.dayOfMonth
            val month = datePicker.month
            val year = datePicker.year

            sharedViewModel.addTransaction(Transactions(transactionName, "$day/${month + 1}/$year", amount, selectedType))
            Toast.makeText(requireContext(), "Transaction added successfully!", Toast.LENGTH_SHORT).show()

            val navController = findNavController()
            navController.navigate(R.id.action_addTransactionFragment_to_cardFragment)

        }
    }

    private fun dateIsSelected(): Boolean {
        return datePicker.year != 0 || datePicker.month != 0 || datePicker.dayOfMonth != 0
    }
}


 */
