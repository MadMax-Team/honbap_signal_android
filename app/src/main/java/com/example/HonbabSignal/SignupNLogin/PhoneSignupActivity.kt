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
    lateinit var phoneNumber: String


   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneSignupBinding.inflate(layoutInflater)
       setContentView(binding.root)
        var status = "번호 입력단계"

       binding.phoneSignupBackBtn.setOnClickListener{
           val intent = Intent(this, LogInActivity::class.java)
           intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
           startActivity(intent)
       }


        binding.phoneSignupKeepBtn.setOnClickListener{
            Log.d("phoneSignupKeepBtn",binding.phoneSignupPhoneEt.text.toString())

            var retrofit = getRetorfit()
            var SignUpService = retrofit.create(SignUpService::class.java)

            if (status == "인증번호 인증완료"){

                //번호 뒤로 같이 넘겨주기


                val intent = Intent(this, SignUpActivity::class.java)
                intent.putExtra("phoneNumber",phoneNumber)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }

            if (status == "인증번호 입력단계") {
                if (binding.phoneSignupInputEt.text.toString().isNotEmpty()) {
                    Log.d("phoneSignupKeepBtn", "인증 번호 입력완")
                    var phoneNumber : String = binding.phoneSignupPhoneEt.text.toString()
                    var verifyCode: String = binding.phoneSignupInputEt.text.toString()


                    SignUpService.phoneVerifySignUp(
                        phoneNumber,
                        verifyCode
                    ).enqueue(object: Callback<SignUpAuthResponse>{
                        override fun onResponse(
                            call: Call<SignUpAuthResponse>,
                            response: Response<SignUpAuthResponse>
                        ) {
                            var respSign = response.body()!!
                            Log.d("SignUpcode",respSign.code.toString())
                            when (respSign.code){
                                5001 -> {
                                    Log.d("phoneSignup","인증번호 일치")
                                    status = "인증번호 인증완료"
                                    binding.phoneSignupMainTv.visibility = View.INVISIBLE
                                    binding.phoneSignupSubTv.visibility = View.INVISIBLE
                                    binding.phoneSignupPhoneEt.visibility = View.INVISIBLE
                                    binding.phoneSignupInputTv.visibility = View.INVISIBLE
                                    binding.phoneSignupInputEt.visibility = View.INVISIBLE
                                    binding.phoneSignupSuccessIc.visibility = View.VISIBLE
                                    binding.phoneSignupSuccessTv1.visibility = View.VISIBLE
                                    binding.phoneSignupSuccessTv2.visibility = View.VISIBLE


                                }
                                5003 -> {
                                    Log.d("phoneSignup","인증번호 불일치")

                                }
                            }
                        }

                        override fun onFailure(call: Call<SignUpAuthResponse>, t: Throwable) {
                            Log.d("phoneSignup","인증번호 확인 오류")
                        }

                    })
                }
            }

            if (status == "번호 입력단계") {
                if (binding.phoneSignupPhoneEt.text.toString().isNotEmpty()) {
                    binding.phoneSignupInputTv.visibility = View.VISIBLE
                    binding.phoneSignupInputEt.visibility = View.VISIBLE
                    status = "인증번호 입력단계"

                    phoneNumber = binding.phoneSignupPhoneEt.text.toString()


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