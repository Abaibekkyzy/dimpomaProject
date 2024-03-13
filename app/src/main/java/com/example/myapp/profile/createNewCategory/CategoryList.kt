package com.example.myapp.profile.createNewCategory

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.myapp.R

object CategoryList{
    fun getCategoryList(context: Context): ArrayList<CategoryData>  {
        val categoryList = arrayListOf<CategoryData>()

        // Добавляем ресурсы изображений в массив
        categoryList.add(CategoryData("housing", ContextCompat.getDrawable(context, R.drawable.category_housing)))
        categoryList.add(CategoryData("food", ContextCompat.getDrawable(context, R.drawable.category_food)))
        categoryList.add(CategoryData("transport", ContextCompat.getDrawable(context, R.drawable.category_transport)))
        categoryList.add(CategoryData("health and medicine", ContextCompat.getDrawable(context, R.drawable.category_health_and_medicine)))
        categoryList.add(CategoryData("entertainment", ContextCompat.getDrawable(context, R.drawable.category_entertainment)))
        categoryList.add(CategoryData("education", ContextCompat.getDrawable(context, R.drawable.category_education)))
        categoryList.add(CategoryData("personal_expenses", ContextCompat.getDrawable(context, R.drawable.category_personal_expenses)))
        categoryList.add(CategoryData("other", ContextCompat.getDrawable(context, R.drawable.category_other)))

        return categoryList
        //gvf
    }
}