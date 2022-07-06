package com.example.HonbabSignal

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getRetorfit(): Retrofit {
    val retrofit = Retrofit.Builder()
        //url교체해야함
        .baseUrl("http://49.50.167.52")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit
}