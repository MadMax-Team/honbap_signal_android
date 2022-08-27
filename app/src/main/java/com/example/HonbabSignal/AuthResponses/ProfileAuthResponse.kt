package com.example.HonbabSignal

import com.google.gson.annotations.SerializedName

data class AuthProfileImg(@SerializedName("type") val type: String,
                          @SerializedName("data") val data: List<String>)

data class AuthProfile(@SerializedName("nickName") val nickName : String,
                       @SerializedName("profileImg") val profileImg: AuthProfileImg,
                       @SerializedName("taste") val taste: String,
                       @SerializedName("hateFood") val hateFood: String,
                       @SerializedName("interest") val interest: String,
                       @SerializedName("avgSpeed") val avgSpeed: String,
                       @SerializedName("preferArea") val preferArea: String,
                       @SerializedName("mbti") val mbti: String,
                       @SerializedName("userIntroduce") val userIntroduce: String,
                       @SerializedName("updateAt") val updateAt: String)

//userIdx GET의 output을 정의
data class ProfileAuthResponse(@SerializedName("isSuccess") val isSuccess: Boolean,
                               @SerializedName("code") val code: Int,
                               @SerializedName("message") val message: String,
                               @SerializedName("result") val result : AuthProfile
                               )

data class ProfileSignalIdxResponse(@SerializedName("isSuccess") val isSuccess: Boolean,
                               @SerializedName("code") val code: Int,
                               @SerializedName("message") val message: String,
                               @SerializedName("result") val result : List<SignalIdxList>
)

data class SignalIdxList(@SerializedName("signalIdx") val signalIdx: Int)

data class ProfilePatchResponse(@SerializedName("isSuccess") val isSuccess : Boolean,
                                @SerializedName("code") val code : Int,
                                @SerializedName("message") val message : String)
