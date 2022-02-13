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
            startActivity(intent)
        }

        //home에서 회원가입 버튼 누르면 signup activity로 넘어갑니다
        binding.homeSignUpTv.setOnClickListener {
            //Toast.makeText(activity, "Its toast!", Toast.LENGTH_SHORT).show();

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.logInMain.setOnClickListener {
            //Toast.makeText(activity, "Its toast!", Toast.LENGTH_SHORT).show();

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //키 해시 구하기
        //val keyHash = Utility.getKeyHash(this)
        //Log.d("Hash", keyHash)

//        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
//            if (error != null) {
//                when {
//                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
//                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
//                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
//                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
//                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
//                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
//                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.ServerError.toString() -> {
//                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
//                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
//                    }
//                    else -> { // Unknown
//                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//            else if (token != null) {
//                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
//                val loginToken = token.accessToken
//                Log.d("LoginToken", loginToken)
//                val intent = Intent(this,SignUpActivity::class.java)
//                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//            }
//        }

        // 카카오 로그인 버튼
        binding.logInKakaoLoginButton.setOnClickListener {


            val intent = Intent(this, KakaoLoginActivity::class.java)
            startActivity(intent)
//            카카오 앱이 있는지 판별
//            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
//                // 있는 경우 앱을 사용하여 로그인
//                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
//            } else {
//                // 없을 경우 웹 페이지를 띄움
//                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
//            }

        }

//        //로그인 상태 유지
//        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
//            if (error != null) {
//                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
//            }
//            else if (tokenInfo != null) {
//                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
//
//                //Log.d("LoginToken", loginToken)
//                //val intent = Intent(this, MainActivity::class.java)
//                //startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//            }
//        }
//
//        //카카오 연결 끊기 부분
//        UserApiClient.instance.unlink { error ->
//            if (error != null) {
//                Log.e("Logout", "연결 끊기 실패", error)
//            } else {
//                // 연결 끊기 성공 한뒤 작업 부분
//            }
//        }
//
//        // 사용자 정보 요청 (기본)
//        UserApiClient.instance.me { user, error ->
//            if (error != null) {
//                Log.e(TAG, "사용자 정보 요청 실패", error)
//            }
//            else if (user != null) {
//                Log.d("userInfo", "사용자 정보 요청 성공" +
//                        "\n이메일: ${user.kakaoAccount?.email}" +
//                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}")
//                nickname = user.kakaoAccount?.profile?.nickname.toString()
//            }
//        }


    }//onCreate꺼임
}