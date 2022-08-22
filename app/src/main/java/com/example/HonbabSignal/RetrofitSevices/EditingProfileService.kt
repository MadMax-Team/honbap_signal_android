package com.example.HonbabSignal


import retrofit2.Call
import retrofit2.http.*

interface EditingProfileService {

    //프로필 정보들을 받아옴
    @GET("/user/mypage")
    fun getUserIdx(
        @Header("x-access-token") jwt: String,
    ): Call<ProfileAuthResponse>


    @FormUrlEncoded
    @PATCH("/user/mypage")
    fun patchProfile(
        @Header("x-access-token") jwt: String,
        @Field("profileImg") profileImg : String,
        @Field("taste") taste : String,
        @Field("hateFood") hateFood : String,
        @Field("interest") interest : String,
        @Field("avgSpeed") avgSpeend : String,
        @Field("preferArea") preferAres : String,
        @Field("mbti") mbti : String,
        @Field("userIntroduce") userIntroduce : String
    ): Call<ProfilePatchResponse>
}