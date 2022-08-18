package com.example.HonbabSignal.RetrofitSevices

import com.example.HonbabSignal.SignalFindAuthResponse
import com.example.HonbabSignal.SignalInfoAuthResponse
import com.example.HonbabSignal.UserInfoAuthResponse
import retrofit2.Call
import retrofit2.http.*

interface MapService {

    @FormUrlEncoded
    @PATCH("/signalFind")
    fun patchSignalFind(
        @Header("x-access-token") jwt: String,
        @Field("latitude") latitude: Double,
        @Field("longitude") longitude: Double
    ): Call<SignalFindAuthResponse>

    @GET("/signalFind/list/{userIdx}")
    fun getSignalInfo(
        @Path("userIdx") userIdx: Int
    ): Call<SignalInfoAuthResponse>
}