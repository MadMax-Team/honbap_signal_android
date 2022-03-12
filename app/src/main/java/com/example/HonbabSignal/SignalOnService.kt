package com.example.HonbabSignal

import com.google.type.DateTime
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface SignalOnService {

    @FormUrlEncoded
    @POST("/signal/{userIdx}/list")
    fun addOnSignal(
        @Path("matchIdx") matchIdx:Int,
        @Field("sigPromiseTime") signalIdx: DateTime,
        @Field("sigPromiseArea") applyIdx: String
    ): Call<SignalOnResponse>
}