package com.felippeneves.newmovieapp.core.data.remote.interceptor

import com.felippeneves.newmovieapp.BuildConfig
import com.felippeneves.newmovieapp.core.util.ApiInfo
import okhttp3.Interceptor
import okhttp3.Response

class ParamsInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(ApiInfo.API_KEY_PARAM, BuildConfig.API_KEY)
            .addQueryParameter(ApiInfo.LANGUAGE_PARAM, ApiInfo.LANGUAGE_PARAM_VALUE)
            .build()

        val newRequest = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(newRequest)
    }
}