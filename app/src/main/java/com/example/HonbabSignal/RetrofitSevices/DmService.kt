package com.example.HonbabSignal.RetrofitSevices

import com.example.HonbabSignal.AuthResponses.*
import com.example.HonbabSignal.UserIdxAuthResponse
import retrofit2.Call
import retrofit2.http.*

interface DmService {

    //쪽지 방 생성
    @FormUrlEncoded
    @POST("/msg")
    fun makeRoom(
        @Header("x-access-token") jwt: String,
        @Field("matchIdx") matchIdx: Int
    ): Call<DmMakeRoomResponse>

    //쪽지 방 확인
    @FormUrlEncoded
    @GET("/msg")
    fun checkRoom(
        @Header("x-access-token") jwt: String
    ): Call<DmRoomCheckResponse>

    //쪽지 보내기
    @FormUrlEncoded
    @POST("/msg/{roomId}")
    fun sendMsg(
        @Header("x-access-token") jwt: String,
        @Path("roomId") roomId: String,
        @Field("text") text: String
    ): Call<DmSendResponse>

    //쪽지 내용 확인
    @FormUrlEncoded
    @GET("msg/{roomId}")
    fun checkContent(
        @Header("x-access-token") jwt: String,
        @Path("roomId") roomId: String,
    ): Call<DmContentCheckResponse>

    //쪽지 방 삭제
    @FormUrlEncoded
    @DELETE("/msg")
    fun DeleteRoom(
        @Header("x-access-token") jwt: String,
        @Path("roomId") roomId: String,
    ): Call<DmRoomDelete>

}