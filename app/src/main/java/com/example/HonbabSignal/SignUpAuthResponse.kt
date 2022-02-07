package com.example.HonbabSignal

import com.google.gson.annotations.SerializedName

//output을 정의하는 곳
data class SignUpAuthResponse(val isSuccess : Boolean, val code: Int, val message : String)

data class UserIdxAuthResponse(@SerializedName("isSuccess") val isSuccess: Boolean,
                               @SerializedName("code") val code: Int,
                               @SerializedName("message") val message: String,
                               @SerializedName("userIdx") val userIdx : Int)
