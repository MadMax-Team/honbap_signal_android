package com.example.HonbabSignal.RetrofitSevices

//import com.google.type.DateTime
import com.example.HonbabSignal.SignalOnResponse
import retrofit2.Call
import retrofit2.http.*

interface SignalService {

    @FormUrlEncoded
    @POST("/signal/list")
    fun addOnSignal(
        @Header("x-access-token") jwt: String
    ): Call<SignalOnResponse>

    @DELETE("/signal/{userIdx}/list")
    fun deleteSignal(
        @Path("userIdx") userIdx: Int
    )
}
