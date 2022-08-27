package com.example.HonbabSignal.Map

data class MapSignal(
    var checkSigWrite: Int = SignalMode.DEFAULT,
    //var profileImg: String,
    var nickName: String? = null,
    var taste: String,
    var hateFood: String,
    var interest: String,
    var avgSpeed: String,
    var preferArea: String,
    var mbti: String,
    var userIntroduce: String,
    var signalIdx: Int,
    var userIdx: Int,
    var sigPromiseTime: String? = null,
    var sigPromiseArea: String? = null,
)

object SignalMode{
    const val DEFAULT = 0 //아무것도 입력안함
    const val CUSTOM = 1 //상세 정보 다 입력함
}
