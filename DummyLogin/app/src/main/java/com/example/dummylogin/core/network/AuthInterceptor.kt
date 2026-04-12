package com.example.dummylogin.core.network

import com.example.dummylogin.core.storage.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sessionManager: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val user = sessionManager.getUser()
        val request = chain.request().newBuilder().apply {
            user?.accessToken?.let {
                addHeader("Authorization", "Bearer ${user.accessToken}")
            }
        }.build()
        return chain.proceed(request)
    }
}