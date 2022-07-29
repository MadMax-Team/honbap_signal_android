package com.example.HonbabSignal

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.HonbabSignal.databinding.ActivityPhoneSignupBinding

class PhoneSignupActivity : Activity() {

    lateinit var binding: ActivityPhoneSignupBinding


   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneSignupBinding.inflate(layoutInflater)
        var status = "번호입력단계"

        binding.phoneSignupKeepBtn.setOnClickListener{
            Log.d("phoneSignupKeepBtn",binding.phoneSignupPhoneEt.text.toString())


            if (status == "인증번호입력단계") {
                if (binding.phoneSignupInputEt.text.toString().isNotEmpty()) {
                    Log.d("phoneSignupKeepBtn", "인증 번호 입력완")
                }
            }

            if (status == "번호입력단계") {
                if (binding.phoneSignupPhoneEt.text.toString().isNotEmpty()) {
                    binding.phoneSignupInputTv.visibility = View.VISIBLE
                    binding.phoneSignupInputEt.visibility = View.VISIBLE
                    status = "인증번호입력단계"
                } else {
                    Log.d("phoneSignupKeepBtn", "폰 번호 입력이 비어있음")
                }
            }

        }


    }


}