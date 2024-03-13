package com.example.myapp.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Carousel.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.databinding.ListExpencesInProfileBinding


class AdapterForCategory(private val categoryL: ArrayList<CategoryArray>): RecyclerView.Adapter<AdapterForCategory.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the layout for each item and return a new ViewHolder object
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_expences_in_profile, parent, false)
        return MyViewHolder(itemView)
    }

    // This method returns the total
    // number of items in the data set
    override fun getItemCount(): Int {
        return categoryL.size
    }

    // This method binds the data to the ViewHolder object
    // for each item in the RecyclerView
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentEmp = categoryL[position]
        //val binding = ListExpencesInProfileBinding.bind(/* view = */ holder)

        holder.nameOfCategory.text = currentEmp.nameCategory
        holder.moneyOfCategory.text = currentEmp.money.toString()
    }

    // This class defines the ViewHolder object for each item in the RecyclerView
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameOfCategory: TextView = itemView.findViewById(R.id.nameOfCategory)
        val moneyOfCategory: TextView = itemView.findViewById(R.id.moneyOfCategory)
    }
}