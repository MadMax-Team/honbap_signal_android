package com.example.HonbabSignal

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://52.78.100.231 : 3001"

fun getRetrofit() : Retrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://52.78.100.231 : 3001")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit

}
