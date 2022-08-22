package com.example.HonbabSignal.DM

import android.provider.ContactsContract

data class Friend(
    val name : String? = null,
    val profileImageUrl : Int? = null,
    val lastString : String? = null,
    val uid : String? = null //채팅창 번호(?)
)

data class Signal(
    val nickname: String? = "닉네임",
    val profileImageUrl: Int? = null,
)
