package com.example.HonbabSignal.RetrofitSevices

import com.example.HonbabSignal.SignUpAuthResponse
import com.example.HonbabSignal.SignalInfoAuthResponse
import com.example.HonbabSignal.UserIdxAuthResponse
import retrofit2.Call
import retrofit2.http.*


interface SignUpService {

    //번호 전송 POST
    @FormUrlEncoded
    @POST("/app/send")
    fun phoneSignUp(
        @Field("phoneNumber") phoneNum: String
    ): Call<SignUpAuthResponse>


    //인증 번호 전송 POST
    @FormUrlEncoded
    @POST("/app/verify")
    fun phoneVerifySignUp(
        @Field("phoneNumber") phoneNum: String,
        @Field("verifyCode") verifyCode: String
    ): Call<SignUpAuthResponse>

    @FormUrlEncoded
    //기본 회원정보를 입력하는 POST
    @POST("/user/signup")
    fun signUp(
        //input을 정의하는곳
        @Field("userId") userId:String,
        @Field("password") password:String,
        @Field("userName") userName:String,
        @Field("birth") birth : String,
        @Field("email") email : String,
        @Field("phoneNum") phoneNum : String,
        @Field("sex") sex: String
    ): Call<SignUpAuthResponse>

    @POST("/user/signup")
    fun SignUpUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("userName") userName: String,
        @Field("nickName") nickName: String,
        @Field("birth") birth: String,
        @Field("phoneNum") phoneNum: String,
        @Field("sex") sex: String
    ): Call<SignUpAuthResponse>

    //유저의 index를 받아오는 GET
    @GET("/user/signup/{userId}")
    fun getUserIdx(
        @Path("userId") userId:String
    ):Call<UserIdxAuthResponse>


    //유저의 프로필 정보를 업로드 하는 POST
    @FormUrlEncoded
    @POST("/user/signup/plusinfo")
    fun profileUp(
        @Field("userIdx") userIdx : Int,
        @Field("nickName") nickName:String,
        @Field("profileImg") profileImg:String,
        @Field("taste") taste:String,
        @Field("hateFood") hateFood:String,
        @Field("interest") interest:String,
        @Field("avgSpeed") avgSpeed:String,
        @Field("preferArea") PreferArea:String,
        @Field("mbti") mbti:String,
        @Field("userIntroduce") userIntroduce:String
    ):Call<SignUpAuthResponse>
}