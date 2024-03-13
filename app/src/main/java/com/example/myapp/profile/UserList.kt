package com.example.myapp.profile

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext

object UserList {

    fun getUserList():ArrayList<User>{
        var userList = ArrayList<User>()
        val c1 = User("Aruzhan", "Nurlan", "aru@gmail.com", "@drawable/a", 1000, 1000, 1000)
        val c2 = User("Aizhan", "Ali","aizhan@gmail.com", "@drawable/a", 3000, 1000, 1000)
        val c3 = User("Dana", "Dari","dana@gmail.com", "@drawable/a", 1000, 1000, 3000)
        val c4 = User("Zhako", "Fazi","zhako@gmail.com", "@drawable/a", 1000, 6000, 1000)
        val c5 = User("Gul", "Bek","gul@gmail.com", "@drawable/a", 6000, 1000, 1000)

        userList.add(c1)
        userList.add(c2)
        userList.add(c3)
        userList.add(c4)
        userList.add(c5)
//        userList.add(c6)

        return userList
    }


}