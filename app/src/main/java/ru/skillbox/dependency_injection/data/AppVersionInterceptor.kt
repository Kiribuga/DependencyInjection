package ru.skillbox.dependency_injection.data

import okhttp3.Interceptor
import okhttp3.Response

class AppVersionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("android_ver", ru.skillbox.dependency_injection.BuildConfig.VERSION_NAME)
            .build()

        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}