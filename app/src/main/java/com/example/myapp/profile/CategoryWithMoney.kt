package com.example.myapp.profile

object CategoryWithMoney {

    fun getCategoryWithMoney():ArrayList<CategoryArray>{
        var categoryList = ArrayList<CategoryArray>()
        val c1 = CategoryArray("Food", 60000)
        val c2 = CategoryArray("Taxi", 30000)
        val c3 = CategoryArray("Home", 200000)
        val c4 = CategoryArray("Shop", 60000)
        val c5 = CategoryArray("Go out", 50000)
        val c6 = CategoryArray("Hobby", 30000)
        categoryList.add(c1)
        categoryList.add(c2)
        categoryList.add(c3)
        categoryList.add(c4)
        categoryList.add(c5)
        categoryList.add(c6)
        return categoryList
    }


}