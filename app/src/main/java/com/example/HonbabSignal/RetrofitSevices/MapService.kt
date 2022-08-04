package com.example.HonbabSignal.RetrofitSevices

import com.example.HonbabSignal.SignalFindAuthResponse
import com.example.HonbabSignal.SignalInfoAuthResponse
import com.example.HonbabSignal.UserInfoAuthResponse
import retrofit2.Call
import retrofit2.http.*

interface MapService {

    @FormUrlEncoded
    @PATCH("/signalFind/{userIdx}")
    fun patchSignalFind(
        @Path("userIdx") userIdx: Int,
        @Field("latitude") latitude: Double,
        @Field("longitude") longitude: Double
    ): Call<SignalFindAuthResponse>

    @GET("/signalFind/list/{userIdx}")
    fun getSignalInfo(
        @Path("userIdx") userIdx: Int
    ): Call<SignalInfoAuthResponse>
}