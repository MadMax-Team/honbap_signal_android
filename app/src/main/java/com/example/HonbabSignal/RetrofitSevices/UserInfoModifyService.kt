package com.example.HonbabSignal.RetrofitSevices

import com.example.HonbabSignal.UserInfoAuthResponse
import com.example.HonbabSignal.UserInfoPatchResponse
import retrofit2.Call
import retrofit2.http.*

interface UserInfoModifyService {

    @GET("/user/myinfo/{userIdx}")
    fun getUserInfo(
        @Header("x-access-token") jwt: String,
        @Path("userIdx") userIdx: Int
    ): Call<UserInfoAuthResponse>

    @FormUrlEncoded
    @PATCH("/user/myinfo/{userIdx}")
    fun patchUserInfo(
        @Header("x-access-token") jwt: String,
        @Path("userIdx") userIdx:Int,
        @Field("userName") userName : String,
        @Field("birth")birth : String
    ): Call<UserInfoPatchResponse>

}