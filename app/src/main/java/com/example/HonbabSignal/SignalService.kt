package com.example.HonbabSignal

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.Path

interface SignalService {

    @POST("/signal/{userIdx}/applylist")
    fun addSignal(
        @Path("userIdx") userIdx:Int,
        @Field("signalIdx") signalIdx: String,
        @Field("applyIdx") applyIdx: String
    ): Call<SignalResponse>

}