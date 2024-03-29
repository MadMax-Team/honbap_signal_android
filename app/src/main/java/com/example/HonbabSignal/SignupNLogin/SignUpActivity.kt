package com.example.HonbabSignal.SignupNLogin

import android.content.Intent
import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.*
import com.example.HonbabSignal.RetrofitSevices.SignUpService
import com.example.HonbabSignal.RetrofitSevices.SignalService
import com.example.HonbabSignal.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity(){

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sex: String = "none"

        //실시간 글자 수 변경
        binding.signUpPwdCheckEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("signUpPwdEt","beforeTextChanged")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("signUpPwdEt","onTextChanged")
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("signUpPwdEt","afterTextChanged")
                val pwdCheck = binding.signUpPwdCheckEt.text.toString()
                val pwd = binding.signUpPwdEt.text.toString()
                if (pwdCheck == pwd) {
                    binding.signUpPwdCheckTfText.text = "비밀번호가 일치합니다."
                }
                else{
                    binding.signUpPwdCheckTfText.text = "비밀번호가 일치하지 않습니다."
                }
            }
        })

        //성별 버튼 체크 확인
        binding.signUpSexManBtn.setOnClickListener {
            if (sex == "F"){
                binding.signUpSexWomanBtn.setBackgroundColor(Color.WHITE)
                binding.signUpSexWomanBtn.setTextColor(Color.BLACK)
            }

            sex = "M"
            binding.signUpSexManBtn.setBackgroundColor(Color.GRAY)
            binding.signUpSexManBtn.setTextColor(Color.WHITE)

        }

        binding.signUpSexWomanBtn.setOnClickListener {
            if (sex == "M"){
                binding.signUpSexManBtn.setBackgroundColor(Color.WHITE)
                binding.signUpSexManBtn.setTextColor(Color.BLACK)
            }

            sex = "F"
            binding.signUpSexWomanBtn.setBackgroundColor(Color.GRAY)
            binding.signUpSexWomanBtn.setTextColor(Color.WHITE)

        }

        binding.signUpNextBtnTv.setOnClickListener {

            //2005: 이메일을 입력해주세요.
            if (binding.signUpEmailEt.text.toString()
                    .isEmpty() || binding.signUpDirectInputEt.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //2006: 이메일은 30자 이내로 입력해주세요.
            if (binding.signUpEmailEt.text.length > 30) {
                Toast.makeText(this, "이메일은 30자 이내로 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //2010: 비밀번호를 입력해주세요.
            if (binding.signUpPwdEt.text.toString().isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.signUpPwdEt.text.toString() != binding.signUpPwdCheckEt.text.toString()) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            //2011: 이름을 입력해주세요.
//            if (binding.signUpNameEt.text.toString().isEmpty()) {
//                Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
            //2012: 생년월일을 입력해주세요.
            if (binding.signUpBirthYearEt.text.toString()
                    .isEmpty() || binding.signUpBirthMonthEt.text.toString().isEmpty()||binding.signUpBirthDayEt.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "생년월일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //2013: 성별을 입력해주세요.
            if (sex == "none") {
                Toast.makeText(this, "성별을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }



            //정보세팅
            //var userId: String = binding.signUpIdEt.text.toString()
            var password : String = binding.signUpPwdEt.text.toString()
            //var userName : String = binding.signUpNameEt.text.toString()
            var userName: String = "name"
            var birth : String = binding.signUpBirthYearEt.text.toString() + "년" + binding.signUpBirthMonthEt.text.toString() + "월" + binding.signUpBirthDayEt.text.toString() + "일"
            var email:String = binding.signUpEmailEt.text.toString() + "@" + binding.signUpDirectInputEt.text.toString()
            var phoneNum : String = intent.getStringExtra("phoneNumber")!!

            var nickName: String = binding.signUpNicknameEt.text.toString()

            var retrofit = getRetorfit()
            var signUpService = retrofit.create(SignUpService::class.java)

            signUpService.signUpUser(email, password, userName, nickName, birth, phoneNum, sex)
                .enqueue(object: Callback<SignUpAuthResponse>{
                    override fun onResponse(
                        call: Call<SignUpAuthResponse>,
                        response: Response<SignUpAuthResponse>
                    ) {
                        val resp = response.body()!!
                        Log.d("SignUp",resp.code.toString())

                        when(resp.code){
                            1000-> {
                                Log.d("SignUp","성공")
                                //회원가입 할 때 POST


                                val intent = Intent(this@SignUpActivity, LogInActivity::class.java)
                                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                startActivity(intent)
                            }
                        }
                    }

                    override fun onFailure(call: Call<SignUpAuthResponse>, t: Throwable) {
                        Log.d("SignUp", "실패")
                    }

                })


        }
    }

}