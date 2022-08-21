package com.example.HonbabSignal.SignupNLogin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding:ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //handler : 워커 쓰레드에서 뷰렌더링하고 싶은데 못하니까 메인 쓰레드에  메시지를 전달해 주는애
        //looper: 메인 쓰레드에 받아온 메시지를 꺼내서 전달해 주는애
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        },2000)//ms
    }

}