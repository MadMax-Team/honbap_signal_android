package com.example.HonbabSignal.RetrofitSevices

//import com.google.type.DateTime
import com.example.HonbabSignal.AuthResponses.SignalOnResponse
import retrofit2.Call
import retrofit2.http.*

interface SignalService {

    @FormUrlEncoded
    @POST("/signal/list")
    fun addOnSignal(
        @Header("x-access-token") jwt: String,
        @Field("sigPromiseTime") sigPromiseTime: String,
        @Field("sigPromiseArea") sigPromiseArea: String
    ): Call<SignalOnResponse>

    @FormUrlEncoded
    @DELETE("/signal/list")
    fun deleteSignal(
        @Header("x-access-token") jwt: String
    )
}
