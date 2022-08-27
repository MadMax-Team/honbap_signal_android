package com.example.HonbabSignal.AuthResponses

import com.google.gson.annotations.SerializedName
import java.time.format.DateTimeFormatter


//방만들때
data class DmMakeRoomResponse(val isSuccess : Boolean, val code: Int, val message : String)


//방확인
data class DmRoomCheckResponse(val isSuccess: Boolean, val code: Int, val message: String, val result : List<String>)

//쪽지보내기
data class DmSendResponse(val isSuccess : Boolean, val code: Int, val message : String)

//쪽지내용확인
data class DmContentCheckResponse(@SerializedName("isSuccess") val isSuccess: Boolean,
                                  @SerializedName("code") val code: Int,
                                  @SerializedName("message") val message: String,
                                  @SerializedName("result") val result : List<DmContentResult>)
//쪽지내용확인 result 형태 정의
data class DmContentResult(@SerializedName("Status") val status: String,
                           @SerializedName("text") val text: String,
                           @SerializedName("sendAt") val sendAt: DateTimeFormatter)

//쪽지방 삭제
data class DmRoomDelete(val isSuccess : Boolean, val code: Int, val message : String)