package com.example.HonbabSignal

import android.app.Application

import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()


        KakaoSdk.init(this, "d522fa00d6dd755ce0565f62e2536a03")
    }
}