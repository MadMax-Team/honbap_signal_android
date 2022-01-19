package com.example.HonbabSignal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity(){

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*이 밑에 있는 코드들은 flo구현 당시 서버에서 api가 넘어올경우 필요했던 코드들 입니다. 일단 남겨놓기는 했는데 api구조가 저희가 배웠던것과 조금
        다르거나 새롭게 만들게 될 경우 자유롭게 지울 수 있습니다*/


//        //회원가입이 되게하는부분
//        binding.signUpSignUpBtnTv.setOnClickListener{
//            signUp()
//
//        }
    }


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
//    private fun signUp() {
//        if (binding.signUpIdEt.text.toString().isEmpty() || binding.signUpDirectInputEt.text.toString().isEmpty())
//        { Toast.makeText(this, "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
//            return
//        }
//        if (binding.signUpPwdEt.text.toString() != binding.signUpPwdCheckEt.text.toString())
//        {Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        val userDB = SongDatabase.getInstance(this)!!
//        userDB.userDao().insert(getUser())
//
//        val users = userDB.userDao().getUsers()
//
//        Log.d("SIGNUPACT",users.toString())
//    }



}