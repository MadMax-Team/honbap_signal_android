package com.example.HonbabSignal

import retrofit2.Call
import retrofit2.http.*

interface SignalService {

    @FormUrlEncoded
    @POST("/signal/{userIdx}/applylist")
    fun addSignal(
        @Path("userIdx") userIdx:Int,
        @Field("signalIdx") signalIdx: String,
        @Field("applyIdx") applyIdx: String
    ): Call<SignalResponse>

}