package com.example.HonbabSignal

data class MapSignal(
    var mode : Int = SignalMode.DEFAULT,
    var isUpdated : Boolean = true,
    var name : String? = null,
    var profileImageUrl : Int? = null,
    var location : String? = null,
    var time: String? = null,
    var tag1: String? = null,
    var tag2: String? = null,
    var tag3: String? = null,
    var tag4: String? = null,
    var tag5: String? = null,
)
object SignalMode{
    const val DEFAULT = 0 //아무것도 입력안함
    const val CUSTOM = 1 //상세 정보 다 입력함
}
