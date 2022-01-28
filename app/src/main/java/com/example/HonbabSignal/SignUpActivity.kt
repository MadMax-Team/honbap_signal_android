package com.example.HonbabSignal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
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
            binding.signUpNickNameErrorTv.visibility = View.INVISIBLE
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
            if (binding.signUpNickNameEt.text.toString().isEmpty()) {
                Toast.makeText(this, "닉네임이 비어있습니다.", Toast.LENGTH_SHORT).show()
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
            var nickname : String = binding.signUpNickNameEt.text.toString()
            var email:String = binding.signUpIdEt.text.toString() + "@" + binding.signUpDirectInputEt.text.toString()
            var phoneNum : String = binding.signUpPhoneNumEt.text.toString()
            var sex : String = binding.signUpSexEt.text.toString()

            //서버로 정보전송

            binding.signUpLoadingPb.visibility = View.VISIBLE

            signUpService.signUp(
                userId,
                password,
                userName,
                nickname,
                email,
                phoneNum,
                sex
            ).enqueue(object:Callback<AuthResponse>{
                //서버와의 통신에 성공했을때(응답값을 받아왔을때) 실행되는 코드
                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    var resp = response.body()!!

                    when(resp.code){
                        1000-> {binding.signUpLoadingPb.visibility = View.GONE
                                finish()}
                        else -> onSignUpFailure(resp.code, resp.message)
                    }

                }
                //서버와의 통신에 실패했을때
                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
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
            2003,2004,3002 ->{
                binding.signUpNickNameErrorTv.visibility = View.VISIBLE
                binding.signUpNickNameErrorTv.text = message
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

//
//    private fun getUser(): User{
//        val email:String = binding.signUpIdEt.text.toString() + "@" + binding.signUpDirectInputEt.text.toString()
//        val pwd: String = binding.signUpPwdEt.text.toString()
//        val name: String = binding.signUpNameEt.text.toString()
//
//        return User(email, pwd, name)
//    }
//
//
//    private fun signUp() {
//        if (binding.signUpIdEt.text.toString()
//                .isEmpty() || binding.signUpDirectInputEt.text.toString().isEmpty()
//        ) {
//            Toast.makeText(this, "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
//            return
//        }
//        if (binding.signUpNameEt.text.toString().isEmpty()) {
//            Toast.makeText(this, "이름 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
//            return
//        }
//        if (binding.signUpPwdEt.text.toString() != binding.signUpPwdCheckEt.text.toString()) {
//            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        val authService = AuthService()
//        authService.setSignUpView(this)
//
//        authService.signUp(getUser())
//
//
//        //val retrofit = Retrofit.Builder().baseUrl("http://13.125.121.202").build()
//
//        Log.d("SIGNUPACT/ASYNC","Hello")
//    }
//
//    override fun onSignUpLoading() {
//        binding.signUpLoadingPb.visibility = View.VISIBLE
//    }
//
//    override fun onSignUpSuccess() {
//        binding.signUpLoadingPb.visibility = View.GONE
//
//        finish()
//    }
//
//    override fun onSignUpFailure(code: Int, message: String) {
//        binding.signUpLoadingPb.visibility = View.GONE
//
//        when(code){
//            2016,2017 -> {
//                binding.signUpEmailErrorTv.visibility = View.VISIBLE
//                binding.signUpEmailErrorTv.text = message
//            }
//        }
//    }



}