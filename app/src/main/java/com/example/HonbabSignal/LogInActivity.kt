package com.example.HonbabSignal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityLogInBinding
import com.example.HonbabSignal.databinding.ActivityMainBinding

class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //home에서 회원가입 버튼 누르면 signup activity로 넘어갑니다
        binding.logInSignUpTv.setOnClickListener{
            startActivity(Intent(this@LogInActivity,SignUpActivity::class.java))
        }
    }

}