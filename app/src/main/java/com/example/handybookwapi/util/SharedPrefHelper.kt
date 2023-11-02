package com.example.handybookwapi.util

import android.content.Context
import android.content.SharedPreferences
import com.example.handybookwapi.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefHelper private constructor(context: Context) {

     private val shared : SharedPreferences = context.getSharedPreferences("data", 0)
     private val edit: SharedPreferences.Editor = shared.edit()
     private val gson  = Gson()


    companion   object {
        private var instance: SharedPrefHelper? = null
        fun getInstance(context: Context): SharedPrefHelper {
            if (instance == null) instance = SharedPrefHelper(context)
            return instance!!
        }
    }

    private val userKEY = "user"
    private val rememberMeKEY = "rememberMe"

    fun setUser(user : User) {
        val temp = gson.toJson(user)
        edit.putString(userKEY, temp).apply()
    }
    fun getUser() : User? {
        val data = shared.getString(userKEY, "")
        if (data == "") return null
        val typeToken = object : TypeToken<User>() {}.type
        return gson.fromJson(data, typeToken)
    }
    fun logout(){
        edit.putString(userKEY, "").apply()
    }
    fun setRememberMe(username:String){
        if (username == "") {
            edit.remove(rememberMeKEY).apply()
        }else{
            edit.putString(rememberMeKEY, username).apply()
        }
    }
    fun getRememberMe(): String? {
        return shared.getString(rememberMeKEY, null)
    }


}