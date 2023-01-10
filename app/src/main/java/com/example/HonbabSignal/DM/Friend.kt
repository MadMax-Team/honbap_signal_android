package com.example.HonbabSignal.DM

import android.provider.ContactsContract

data class DmRoom(
    val name: String? = null, //닉네임
    val profileImageUrl: Int? = null,
    val lastString: String? = null, //마지막 메세지
    val lastTime: String? = null, //마지막 대화시간
    val roomId: String //roomId
)

data class Signal(
    val nickname: String? = "닉네임",
    val profileImageUrl: Int? = null,
)
