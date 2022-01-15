package com.example.HonbabSignal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityMainBinding
import com.example.HonbabSignal.databinding.ActivityMapBinding
import com.naver.maps.map.NaverMapSdk

class MapActivity:AppCompatActivity() {

    lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("hs74nz38zh")

    }




}