package com.example.HonbabSignal.RetrofitSevices

//import com.google.type.DateTime
import android.text.Editable
import com.example.HonbabSignal.AuthResponses.SignalOnResponse
import com.example.HonbabSignal.ProfileAuthResponse
import com.example.HonbabSignal.ProfilePatchResponse
import com.example.HonbabSignal.ProfileSignalIdxResponse
import com.example.HonbabSignal.ProfileSignalNicknameResponse
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
    @POST("/signal/applylist")
    fun addSignal(
        @Header("x-access-token") jwt: String?,
        @Field("signalIdx") signalIdx: Int,
        @Field("applyedIdx") applyedIdx: Int
    ) : Call<SignalOnResponse>

    @DELETE("/signal/list")
    fun deleteSignal(
        @Header("x-access-token") jwt: String,
        @Field("signalIdx") signalIdx: ArrayList<Int>
    ): Call<SignalOnResponse>

    @FormUrlEncoded
    @PATCH("/signal/list")
    fun patchSignal(
        @Header("x-access-token") jwt: String,
        @Field("sigPromiseTime") sigPromiseTime: String,
        @Field("sigPromiseArea") sigPromiseArea: String,
        @Field("sigStart") sigStart: String
    ): Call<ProfilePatchResponse>

    @GET("/mysignal")
    fun getSignalIdx(
        @Header("x-access-token") jwt: String,
    ): Call<ProfileSignalIdxResponse>

    @GET("/signal/applylist")
    fun getSignalToMe(
        @Header("x-access-token") jwt: String,
    ): Call<ProfileSignalNicknameResponse>
}
