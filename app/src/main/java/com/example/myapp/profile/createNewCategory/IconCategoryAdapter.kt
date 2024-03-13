package com.example.myapp.profile.createNewCategory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R

class IconCategoryAdapter(private val context: Context, private val categoryList: List<CategoryData>) :
    RecyclerView.Adapter<IconCategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.icon_category_item, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = categoryList[position]
        holder.imageView.setImageDrawable(currentItem.categoryIcon)
        holder.textView.text = currentItem.categoryName
    }

    override fun getItemCount() = categoryList.size

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.categoryImageView)
        val textView: TextView = itemView.findViewById(R.id.categoryTextView)

        init {
            // Устанавливаем ширину элемента равной трети ширины экрана
            val displayMetrics = context.resources.displayMetrics
            val screenWidth = displayMetrics.widthPixels
            val layoutParams = itemView.layoutParams
            layoutParams.width = screenWidth / 3
            itemView.layoutParams = layoutParams
        }
    }
}
