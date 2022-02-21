package com.example.HonbabSignal

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityLogInBinding
import com.kakao.auth.AuthType
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding

    lateinit var nickname: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //home에서 login버튼 누르면 login activity로 넘어갑니다
        binding.homeLoginTv.setOnClickListener {
            //Toast.makeText(activity, "Its toast!", Toast.LENGTH_SHORT).show();

            val intent = Intent(this, LogInActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }

        //home에서 회원가입 버튼 누르면 signup activity로 넘어갑니다
        binding.homeSignUpTv.setOnClickListener {
            //Toast.makeText(activity, "Its toast!", Toast.LENGTH_SHORT).show();

            val intent = Intent(this, SignUpActivity::class.java)
            //intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }

        binding.homeLoginTv.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }


        // 카카오 로그인 버튼
        binding.logInKakaoLoginButton.setOnClickListener {

            val intent = Intent(this, KakaoLoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }

    }//onCreate꺼임
}