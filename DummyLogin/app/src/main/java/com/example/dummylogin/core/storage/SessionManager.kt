package com.example.dummylogin.core.storage

import android.content.Context

class SessionManager(context: Context) {

    val TOKEN_KEY = "token";

    private val prefs = context.getSharedPreferences("app", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken(): String? {
        return prefs.getString(TOKEN_KEY, null)
    }

    fun clear(){
        prefs.edit().clear().apply()
    }
}