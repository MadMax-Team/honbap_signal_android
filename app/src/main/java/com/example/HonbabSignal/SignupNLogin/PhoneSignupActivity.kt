package com.example.HonbabSignal.SignupNLogin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.RetrofitSevices.SignUpService
import com.example.HonbabSignal.SignUpAuthResponse
import com.example.HonbabSignal.databinding.ActivityPhoneSignupBinding
import com.example.HonbabSignal.getRetorfit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneSignupActivity : AppCompatActivity() {

    lateinit var binding: ActivityPhoneSignupBinding


   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneSignupBinding.inflate(layoutInflater)
       setContentView(binding.root)
        var status = "번호 입력단계"

        binding.phoneSignupKeepBtn.setOnClickListener{
            Log.d("phoneSignupKeepBtn",binding.phoneSignupPhoneEt.text.toString())


            if (status == "인증번호 인증완료"){
                val intent = Intent(this, SignUpActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }

            if (status == "인증번호 입력단계") {
                if (binding.phoneSignupInputEt.text.toString().isNotEmpty()) {
                    Log.d("phoneSignupKeepBtn", "인증 번호 입력완")

                        
                    //인증번호 일치 시 
                    // status = "인증번호 인증완료"
                }
            }

            if (status == "번호 입력단계") {
                if (binding.phoneSignupPhoneEt.text.toString().isNotEmpty()) {
                    binding.phoneSignupInputTv.visibility = View.VISIBLE
                    binding.phoneSignupInputEt.visibility = View.VISIBLE
                    status = "인증번호 입력단계"

                    var phoneNumber : String = binding.phoneSignupPhoneEt.text.toString()
                    var retrofit = getRetorfit()
                    var SignUpService = retrofit.create(SignUpService::class.java)

                    SignUpService.phoneSignUp(
                        phoneNumber
                    ).enqueue(object: Callback<SignUpAuthResponse>{
                        override fun onResponse(
                            call: Call<SignUpAuthResponse>,
                            response: Response<SignUpAuthResponse>
                        ) {
                            var respSign = response.body()!!
                            Log.d("SignUpcode",respSign.code.toString())
                            when (respSign.code){
                                5000 -> {
                                    Log.d("phoneSignup","인증번호 전송 완료")
                                }
                                5002 -> {
                                    Log.d("phoneSignup","인증번호 전송 실패")

                                }
                            }
                        }

                        override fun onFailure(call: Call<SignUpAuthResponse>, t: Throwable) {
                            Log.d("phoneSignup","인증번호 전송 오류")

                        }

                    })
                } else {
                    Log.d("phoneSignupKeepBtn", "폰 번호 입력이 비어있음")
                }
            }
        }


    }


}