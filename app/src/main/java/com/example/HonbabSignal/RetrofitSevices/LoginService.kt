package com.example.HonbabSignal.RetrofitSevices

import com.example.HonbabSignal.LoginAuthResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST("/user/signin")
    fun login(
        @Field("userId") userId : String,
        @Field("password") pasword : String
    ): Call<LoginAuthResponse>

}