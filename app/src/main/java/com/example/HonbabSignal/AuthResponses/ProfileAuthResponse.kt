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
                               @SerializedName("result") val result : ArrayList<SignalIdxList>
)

data class SignalIdxList(@SerializedName("signalIdx") val signalIdx: Int)

data class ProfileSignalNicknameResponse(@SerializedName("isSuccess") val isSuccess: Boolean,
                                    @SerializedName("code") val code: Int,
                                    @SerializedName("message") val message: String,
                                    @SerializedName("result") val result : List<SignalNicknameList>
)

data class SignalNicknameList(@SerializedName("nickName") val nickName: String)

data class ProfilePatchResponse(@SerializedName("isSuccess") val isSuccess : Boolean,
                                @SerializedName("code") val code : Int,
                                @SerializedName("message") val message : String)

data class defaultResponse(@SerializedName("isSuccess") val isSuccess : Boolean,
                           @SerializedName("code") val code : Int,
                           @SerializedName("message") val message : String)

data class dmRoomListResponse(@SerializedName("isSuccess") val isSuccess : Boolean,
                           @SerializedName("code") val code : Int,
                           @SerializedName("message") val message : String,
                            @SerializedName("result") val result: List<RoomIdList>)

data class RoomIdList(@SerializedName("roomId") val roomId:String)

data class SignalInfoFromNicknameResponse(@SerializedName("isSuccess") val isSuccess : Boolean,
                                  @SerializedName("code") val code : Int,
                                  @SerializedName("message") val message : String,
                                  @SerializedName("result") val result: SignalProfileInfo

)

data class SignalProfileInfo(@SerializedName("userIdx") val userIdx : Int,
                       @SerializedName("profileImg") val profileImg: String,
                       @SerializedName("taste") val taste: String,
                       @SerializedName("hateFood") val hateFood: String,
                       @SerializedName("interest") val interest: String,
                       @SerializedName("avgSpeed") val avgSpeed: String,
                       @SerializedName("preferArea") val preferArea: String,
                       @SerializedName("mbti") val mbti: String,
                       @SerializedName("userIntroduce") val userIntroduce: String,
                             @SerializedName("updateAt") val updateAt : String,
                       @SerializedName("nickName") val nickName : String,)