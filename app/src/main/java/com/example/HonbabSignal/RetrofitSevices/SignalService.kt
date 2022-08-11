package com.example.HonbabSignal.RetrofitSevices

//import com.google.type.DateTime
import com.example.HonbabSignal.SignalOnResponse
import retrofit2.Call
import retrofit2.http.*

interface SignalService {

    @FormUrlEncoded
    @POST("/signal/{userIdx}/list")
    fun addOnSignal(
        @Path("userIdx") userIdx:Int,
        @Field("sigPromiseTime") signalIdx: String, //DateTime
        @Field("sigPromiseArea") applyIdx: String
    ): Call<SignalOnResponse>

    @DELETE("/signal/{userIdx}/list")
    fun deleteSignal(
        @Path("userIdx") userIdx: Int
    )
}
