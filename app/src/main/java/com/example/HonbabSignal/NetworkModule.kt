package com.example.HonbabSignal

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun getRetorfit(): Retrofit {

    val client: OkHttpClient.Builder = OkHttpClient.Builder()
    client.connectTimeout(100, TimeUnit.SECONDS)
    client.readTimeout(100, TimeUnit.SECONDS)
    client.writeTimeout(100, TimeUnit.SECONDS)
    client.build()

    val retrofit = Retrofit.Builder()
        //url교체해야함
        .baseUrl("http://49.50.167.52")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit
}