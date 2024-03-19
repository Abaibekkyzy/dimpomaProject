package com.example.myapp.profile.createNewCategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapp.R
import com.example.myapp.databinding.FragmentCreateNewCategoryBinding


class CreateNewCategoryFragment : Fragment() {

    private lateinit var binding : FragmentCreateNewCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNewCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireContext()
        val categoryList = CategoryList.getCategoryList(context)
        val adapter = IconCategoryAdapter(context, categoryList)

        binding.RecyclerViewIcon.adapter = adapter
        binding.RecyclerViewIcon.layoutManager = GridLayoutManager(context, 3)
        val navController = findNavController()
        val BackBtn: ImageView = binding.backButtonFromCreateCategory

        BackBtn.setOnClickListener{
            navController.navigate(R.id.action_createNewCategoryFragment_to_profileFragment)
        }
    }
}