package com.example.HonbabSignal

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface SignUpService {
    @FormUrlEncoded
    @POST("/user/signup")
    fun signUp(
        //input을 정의하는곳
        @Field("userId") userId:String,
        @Field("password") password:String,
        @Field("userName") userName:String,
        @Field("nickName") nickName : String,
        @Field("email") email : String,
        @Field("phoneNum") phoneNum : String,
        @Field("sex") sex: String
    ): Call<AuthResponse>
}