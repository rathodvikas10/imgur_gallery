package com.example.network.retrofit

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to add client-id to requests
 * @param clientId client-id to add to requests
 */
class AuthenticationInterceptor(private val clientId: String) : Interceptor {
    private companion object {
        private const val HEADER_NAME = "Authorization"
        private const val HEADER_KEY = "Client-ID"
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader(HEADER_NAME, "$HEADER_KEY $clientId")
        val request = builder.build()
        return chain.proceed(request)
    }
}
