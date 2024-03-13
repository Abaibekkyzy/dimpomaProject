package com.example.myapp.profile

import android.graphics.drawable.Drawable

data class User(
    var name: String,
    val surname: String,
    val email: String,
    val image: String, //Drawable
    val balance: Int,
    val income: Int,
    val outcome: Int
):java.io.Serializable{
    companion object {
        fun createUser(name: String, surname: String,email: String, image: String, balance: Int, income: Int, outcome: Int): User {
            return User(name, surname,email, image, balance, income, outcome)
        }

    }


}
