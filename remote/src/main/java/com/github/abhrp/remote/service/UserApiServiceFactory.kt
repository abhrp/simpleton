package com.github.abhrp.remote.service

import com.github.abhrp.remote.constants.NetworkConstants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object UserApiServiceFactory {
    fun getUserApiService(isDebug: Boolean): UserApiService {
        val okHttpClient = makeOkHttpClient(makeHttpLoggingInterceptor(isDebug))
        return makeUserApiService(okHttpClient)
    }

    private fun makeUserApiService(okHttpClient: OkHttpClient): UserApiService {
        val retrofit = Retrofit.Builder().baseUrl(NetworkConstants.BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        return retrofit.create(UserApiService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build()

    private fun makeHttpLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging
    }
}