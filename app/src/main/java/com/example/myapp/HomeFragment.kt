package com.example.myapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.FragmentHomeBinding
import java.util.Locale

class HomeFragment : Fragment() {

    private lateinit var adapter: YoutubeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var editTextSearch: EditText
    private lateinit var buttonSearch: Button
    private lateinit var buttonBack: ImageView
    private lateinit var noResultsTextView: TextView
    private lateinit var noResultsImageView: ImageView

    private val allVideoIds = listOf(
        "4WywHA7UqW4",
        "U4mADkt6o-M",
        "IzFxknLfXKs"
    )
    private val allDescriptions = listOf(
        "JMIN - Body (Feat. 식케이 (Sik-K)) (Official Video)",
        "21 Savage - redrum (Official Music Video)",
        "Marv - Ok (with Jay Park) [M/V]"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root


        val layoutManager = LinearLayoutManager(context)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        adapter = YoutubeAdapter(allVideoIds, allDescriptions)
        recyclerView.adapter = adapter

        editTextSearch = binding.editTextSearch
        buttonSearch = binding.buttonSearch
        buttonBack = binding.buttonBack
        noResultsTextView = binding.noResultsTextView
        noResultsImageView = binding.noResultsImageView

        buttonSearch.setOnClickListener {
            searchVideos()
            buttonBack.visibility = View.VISIBLE
            hideKeyboard()
        }

        buttonBack.setOnClickListener {
            buttonBack.visibility = View.GONE
            editTextSearch.setText("")
            noResultsTextView.visibility = View.GONE
            noResultsImageView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            adapter.updateData(allVideoIds, allDescriptions)
        }

        return view
    }

    private fun searchVideos() {
        val filteredVideoIds = mutableListOf<String>()
        val filteredDescriptions = mutableListOf<String>()

        val query = getSearchedText()
        for (i in allDescriptions.indices) {
            if (allDescriptions[i].lowercase(Locale.getDefault()).contains(query)) {
                filteredVideoIds.add(allVideoIds[i])
                filteredDescriptions.add(allDescriptions[i])
            }
        }
        if (filteredVideoIds.isEmpty()) {
            searchFoundNothing()
        } else {
            showVideos(filteredVideoIds, filteredDescriptions)
        }
    }


    fun getSearchedText() = editTextSearch.text.toString().trim().lowercase(Locale.getDefault())

    fun searchFoundNothing() {
            // Show no results message and image
            noResultsTextView.visibility = View.VISIBLE
            noResultsImageView.visibility = View.VISIBLE

            // Hide RecyclerView or other views if needed
            recyclerView.visibility = View.GONE

    }

    fun showVideos(filteredVideoIds: MutableList<String>, filteredDescriptions: MutableList<String>, ){
            // Show search results
            noResultsTextView.visibility = View.GONE
            noResultsImageView.visibility = View.GONE

            // Show RecyclerView or other views if needed
            recyclerView.visibility = View.VISIBLE
            adapter.updateData(filteredVideoIds, filteredDescriptions)

    }

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editTextSearch.windowToken, 0)
    }

}

