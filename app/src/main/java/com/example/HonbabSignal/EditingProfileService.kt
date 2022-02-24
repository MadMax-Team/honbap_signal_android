package com.example.HonbabSignal


import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.Call

interface EditingProfileService {

    //프로필 정보들을 받아옴
    @GET("/user/mypage/{userIdx}")
    fun getUserIdx(
        @Path("userIdx") userIdx:Int = 1
    ): Call<ProfileAuthResponse>
}