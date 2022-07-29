package com.example.HonbabSignal

import android.app.Activity
import android.os.Bundle
import com.example.HonbabSignal.databinding.ActivityPhoneSignupBinding

class PhoneSignupActivity : Activity() {

    lateinit var binding: ActivityPhoneSignupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneSignupBinding.inflate(layoutInflater)


    }
}