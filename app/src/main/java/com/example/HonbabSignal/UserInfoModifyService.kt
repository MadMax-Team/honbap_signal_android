package com.example.HonbabSignal

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserInfoModifyService {

    @GET("/user/myinfo/{userIdx}")
    fun getUserInfo(
        @Path("userIdx") userIdx: Int
    ): Call<UserInfoAuthResponse>

}