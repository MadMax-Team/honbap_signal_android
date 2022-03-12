package com.example.HonbabSignal

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewService {
    @FormUrlEncoded
    @POST("/comment/newcomment")
    fun addSignal(
        @Field("signalIdx") signalIdx: Int,
        @Field("userIdx") userIdx: Int,
        @Field("writerIdx") writerIdx: Int,
        @Field("comment") comment: String,
        @Field("star") start: Int
    ): Call<ReviewResponse>
}