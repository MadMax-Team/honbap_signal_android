package com.example.HonbabSignal.RetrofitSevices

import com.example.HonbabSignal.LoginAuthResponse
import com.example.HonbabSignal.LogininUserIdxResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST("/user/login")
    fun login(
        @Field("email") userId : String,
        @Field("password") password : String
    ): Call<LoginAuthResponse>

    @GET("/user/signup/admin")
    fun getUserIdx(): Call<LogininUserIdxResponse>

}
