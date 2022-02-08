package com.example.HonbabSignal

import com.google.gson.annotations.SerializedName

//회원정보와 프로필의 업로드에 필요한 output정의
data class SignUpAuthResponse(val isSuccess : Boolean, val code: Int, val message : String)

//userIdx의 result의 형태를 정의
data class AuthIdx(@SerializedName("userIdx")val userIdx : Int)
//userIdx GET의 output을 정의
data class UserIdxAuthResponse(@SerializedName("isSuccess") val isSuccess: Boolean,
                               @SerializedName("code") val code: Int,
                               @SerializedName("message") val message: String,
                               @SerializedName("result") val result : List<AuthIdx>)
