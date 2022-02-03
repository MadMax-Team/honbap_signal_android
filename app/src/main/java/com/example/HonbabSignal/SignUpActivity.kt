package com.example.HonbabSignal

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivitySignUpBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class SignUpActivity : AppCompatActivity(){

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signUpSignUpBtnTv.setOnClickListener{

            binding.signUpIdErrorTv.visibility = View.INVISIBLE
            binding.signUpEmailErrorTv.visibility = View.INVISIBLE
            binding.signUpPhoneNumErrorTv.visibility = View.INVISIBLE
            //retrofit 개체 생성
            var retrofit = Retrofit.Builder()
                .baseUrl("http://52.78.100.231:3001")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            //retrofit에 interface를 넣어줌
            var signUpService = retrofit.create(SignUpService::class.java)
            //밑으로 retrofit사용

            //비어있는거 check
            if (binding.signUpIdEt.text.toString().isEmpty()) {
                Toast.makeText(this, "아이디가 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.signUpPwdEt.text.toString().isEmpty()) {
                Toast.makeText(this, "비밀번호가 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.signUpNameEt.text.toString().isEmpty()) {
                Toast.makeText(this, "이름이 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.signUpEmailEt.text.toString()
                    .isEmpty() || binding.signUpDirectInputEt.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.signUpPhoneNumEt.text.toString().isEmpty()) {
                Toast.makeText(this, "전화번호가 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.signUpSexEt.text.toString().isEmpty()) {
                Toast.makeText(this, "성별이 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.signUpPwdEt.text.toString() != binding.signUpPwdCheckEt.text.toString()) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //정보세팅
            var userId: String = binding.signUpIdEt.text.toString()
            var password : String = binding.signUpPwdEt.text.toString()
            var userName : String = binding.signUpNameEt.text.toString()
            var email:String = binding.signUpIdEt.text.toString() + "@" + binding.signUpDirectInputEt.text.toString()
            var phoneNum : String = binding.signUpPhoneNumEt.text.toString()
            var sex : String = binding.signUpSexEt.text.toString()

            //서버로 정보전송

            binding.signUpLoadingPb.visibility = View.VISIBLE

            signUpService.signUp(
                userId,
                password,
                userName,
                email,
                phoneNum,
                sex
            ).enqueue(object:Callback<SignUpAuthResponse>{
                //서버와의 통신에 성공했을때(응답값을 받아왔을때) 실행되는 코드
                override fun onResponse(call: Call<SignUpAuthResponse>, responseSignUp: Response<SignUpAuthResponse>) {
                    var resp = responseSignUp.body()!!

                    when(resp.code){
                        1000-> {binding.signUpLoadingPb.visibility = View.GONE
                                finish()}
                        else -> onSignUpFailure(resp.code, resp.message)
                    }

                }
                //서버와의 통신에 실패했을때
                override fun onFailure(call: Call<SignUpAuthResponse>, t: Throwable) {
                    Log.d("DEBUG", t.message.toString())
                    var dialog = AlertDialog.Builder(this@SignUpActivity)

                    dialog.setTitle("실패!")
                    dialog.setMessage("통신에 실패했습니다!")
                    dialog.show()
                    binding.signUpLoadingPb.visibility = View.GONE
                }
            })
        }
    }
    fun onSignUpFailure(code: Int, message: String) {
        binding.signUpLoadingPb.visibility = View.GONE

        when(code){
            2001,2002,3001 -> {
                binding.signUpIdErrorTv.visibility = View.VISIBLE
                binding.signUpIdErrorTv.text = message
            }
            2005,2006,2007,3003 ->{
                binding.signUpEmailErrorTv.visibility = View.VISIBLE
                binding.signUpEmailErrorTv.text = message
            }
            2008,2009,3004 ->{
                binding.signUpPhoneNumErrorTv.visibility = View.VISIBLE
                binding.signUpPhoneNumErrorTv.text = message
            }
        }
    }

}