package com.example.HonbabSignal.RetrofitSevices

//import com.google.type.DateTime
import android.text.Editable
import com.example.HonbabSignal.*
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
    @POST("/signal/applylist")
    fun addSignal(
        @Header("x-access-token") jwt: String?,
        @Field("signalIdx") signalIdx: Int,
        @Field("applyedIdx") applyedIdx: Int
    ) : Call<SignalOnResponse>

    @FormUrlEncoded
    @HTTP(method="DELETE", hasBody=true, path="/signal/list")
    //@DELETE("/signal/list")
    fun deleteSignal(
        @Header("x-access-token") jwt: String,
        @Field("signalIdx") signalIdx: Int
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

    @FormUrlEncoded
    @POST("/signal/info")
    fun getSignalInfoFromNickname(
        @Field("nickName") nickName: String
    ): Call<SignalInfoFromNicknameResponse>


    //쪽지방 생성
    @FormUrlEncoded
    @POST("/msg")
    fun makeDmRoom(
        @Header("x-access-token") jwt: String,
        @Field("matchIdx") matchIdx: Int //시그널 신청한 사람의 식별자 (시그널 보낸 사람/ 수락한 사람 아님)
    ): Call<defaultResponse>


    //쪽지방 확인
    @GET("/msg")
    fun getDmRoomList(
        @Header("x-access-token") jwt: String
    ): Call<dmRoomListResponse>
}
