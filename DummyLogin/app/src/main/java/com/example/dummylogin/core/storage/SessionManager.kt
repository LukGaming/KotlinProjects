package com.example.dummylogin.core.storage

import android.content.Context
import com.example.dummylogin.features.auth.data.model.LoginResponse
import com.google.gson.Gson

class SessionManager(context: Context) {

    val USER_STORAGE_KEY = "token";

    private val prefs = context.getSharedPreferences("app", Context.MODE_PRIVATE)

    fun saveUser(user: LoginResponse) {
        val userJson = Gson().toJson(user)
        prefs.edit().putString(USER_STORAGE_KEY, userJson).apply()
    }

    fun isLogged(): Boolean {
        return getUser() != null;
    }

    fun getUser(): LoginResponse? {
        val json = prefs.getString(USER_STORAGE_KEY, null) ?: return null
        return Gson().fromJson(json, LoginResponse::class.java)
    }

    fun clear(){
        prefs.edit().clear().apply()
    }
}