package com.example.HonbabSignal.SignupNLogin

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.*
import com.example.HonbabSignal.RetrofitSevices.LoginService
import com.example.HonbabSignal.databinding.ActivityLogInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding

    lateinit var nickname: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //login이 성공적으로 되면 home으로 넘어갑니다.
        binding.homeLoginTv.setOnClickListener {

            //retrofit 개체 생성
            var retrofit = getRetorfit()

            //retrofit에 interface를 넣어줌
            var loginService = retrofit.create(LoginService::class.java)
            //밑으로 retrofit사용

            //정보 세팅
            var userId = binding.homeEmailIdEt.text.toString()
            var pwd = binding.homePwdEt.text.toString()
            //레트로핏 작동!!

            loginService.login(userId, pwd).enqueue(object : Callback<LoginAuthResponse>{
                override fun onResponse(
                    call: Call<LoginAuthResponse>,
                    response: Response<LoginAuthResponse>
                ) {
                    val respLogin = response.body()!!
                    Log.d("responseCodeFromLogin",respLogin.code.toString())
                    when(respLogin.code){
                        1000-> {


                            //sharedPreference에 userIdx를 넣어주었습니다.
                            val spf_userIdx = getSharedPreferences("userIdx",0)
                            val editor_userIdx = spf_userIdx.edit()
                            editor_userIdx.putInt("userIdx",respLogin.result.userIdx)
                            editor_userIdx.apply()
                            Log.d("spf에 userIdx",spf_userIdx.getInt("userIdx",-1).toString())

                            //sharedPreference에 jwt를 넣어주었습니다.

                            val spf_jwt = getSharedPreferences("jwt",0)
                            val editor_jwt = spf_jwt.edit()
                            editor_jwt.putString("jwt",respLogin.result.jwt)
                            editor_jwt.apply()
                            Log.d("spf에 jwt",spf_jwt.getString("jwt","").toString())

                            val intent = Intent(this@LogInActivity, MainActivity::class.java)
                            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            startActivity(intent)
                            Toast.makeText(this@LogInActivity,"로그인 성공!",Toast.LENGTH_SHORT).show()
                        }
                        2038 ->{Toast.makeText(this@LogInActivity,respLogin.message,Toast.LENGTH_SHORT).show()}
                        4000 ->{Toast.makeText(this@LogInActivity,respLogin.message, Toast.LENGTH_SHORT).show()}
                        2040 ->{Toast.makeText(this@LogInActivity,respLogin.message, Toast.LENGTH_SHORT).show()}
                        2026 ->{Toast.makeText(this@LogInActivity,respLogin.message, Toast.LENGTH_SHORT).show()}
                    }

                }

                override fun onFailure(call: Call<LoginAuthResponse>, t: Throwable) {
                    Log.d("DEBUGInSignUp", t.message.toString())
                    var dialog = AlertDialog.Builder(this@LogInActivity)

                    dialog.setTitle("실패!")
                    dialog.setMessage("통신에 실패했습니다!")
                    dialog.show()
                }

            })
        }

        //Login에서 회원가입 버튼 누르면 signup activity로 넘어갑니다
        binding.homeSignUpTv.setOnClickListener {
            //Toast.makeText(activity, "Its toast!", Toast.LENGTH_SHORT).show();

            val intent = Intent(this, PhoneSignupActivity::class.java)
            //intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        // 카카오 로그인 버튼
        binding.logInKakaoLoginButton.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        //후기로 넘어가는 버튼
        binding.loginToPostScriptBtn.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        //map list로 넘어가는 버튼
        binding.loginToMapListBtn.setOnClickListener {

            val intent = Intent(this, MapActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)

        }
    }//onCreate꺼임
}