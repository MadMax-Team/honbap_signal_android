package com.example.HonbabSignal

import com.google.gson.annotations.SerializedName

////회원정보와 프로필의 업로드에 필요한 output정의
//data class SignUpAuthResponse(val isSuccess : Boolean, val code: Int, val message : String)
//
////userIdx의 result의 형태를 정의
//data class AuthIdx(@SerializedName("userIdx")val userIdx : Int)
////userIdx GET의 output을 정의
//data class UserIdxAuthResponse(@SerializedName("isSuccess") val isSuccess: Boolean,
//                               @SerializedName("code") val code: Int,
//                               @SerializedName("message") val message: String,
//                               @SerializedName("result") val result : List<AuthIdx>)

data class UserInfoAuthResponse(@SerializedName("isSuccess") val isSuccess: Boolean,
                                @SerializedName("code") val code: Int,
                                @SerializedName("message") val message: String,
                                @SerializedName("result") val result : UserInfoDetail)

data class UserInfoDetail(@SerializedName("userId") val userId : String,
                          @SerializedName("userName") val userName : String,
                          @SerializedName("birth") val birth : String,
                          @SerializedName("email") val email : String,
                          @SerializedName("phoneNum") val phoneNum : String,
                          @SerializedName("sex") val sex : String,
                          @SerializedName("updateAt") val updateAt : String,
                          @SerializedName("createAt") val createAt : String)

data class SignalInfoAuthResponse(@SerializedName("isSuccess") val isSuccess: Boolean,
                                @SerializedName("code") val code: Int,
                                @SerializedName("message") val message: String,
                                @SerializedName("result") val result : List<String>)

data class SignalInfoDetail(@SerializedName("userId") val userId: Int,
//                          @SerializedName("userName") val userName : String,
//                          @SerializedName("birth") val birth : String,
//                          @SerializedName("email") val email : String,
//                          @SerializedName("phoneNum") val phoneNum : String,
//                          @SerializedName("sex") val sex : String,
//                          @SerializedName("updateAt") val updateAt : String,
//                          @SerializedName("createAt") val createAt : String
                          )

data class UserInfoPatchResponse(@SerializedName("isSuccess") val isSuccess : Boolean,
                                 @SerializedName("code") val code : Int,
                                 @SerializedName("message") val message : String)

//로그인할때 사용되는 response
data class LoginAuthResponse(@SerializedName("isSuccess") val isSuccess : Boolean,
                             @SerializedName("code") val code : Int,
                             @SerializedName("message") val message : String,
                             @SerializedName("result")val result : LoginInfo)

data class LoginInfo(@SerializedName("userIdx") val userIdx : Int,
                    @SerializedName("jwt") val jwt : String)