package com.example.HonbabSignal

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

        binding.signUpNextBtnTv.setOnClickListener {

            //2001: 아이디가 비어있습니다.
            if (binding.signUpIdEt.text.toString().isEmpty()) {
                Toast.makeText(this, "아이디가 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //2002: 아이디는 5~20자 이내로 입력해주세요.
            if (binding.signUpIdEt.text.length < 5 || binding.signUpIdEt.text.length > 20) {
                Toast.makeText(this, "아이디는 5~20자 이내로 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
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
            //2008: 휴대폰 번호를 입력해주세요.
            if (binding.signUpPhoneNumEt.text.toString().isEmpty()) {
                Toast.makeText(this, "휴대폰 번호가 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //2009: 휴대폰 번호는 11자 이내로 입력해주세요.
            if (binding.signUpPhoneNumEt.text.length > 11) {
                Toast.makeText(this, "휴대폰 번호는 11자 이내로 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //2010: 비밀번호를 입력해주세요.
            if (binding.signUpPwdEt.text.toString().isEmpty()) {
                Toast.makeText(this, "비밀번호가 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.signUpPwdEt.text.toString() != binding.signUpPwdCheckEt.text.toString()) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //2011: 이름을 입력해주세요.
            if (binding.signUpNameEt.text.toString().isEmpty()) {
                Toast.makeText(this, "이름이 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //2012: 생년월일을 입력해주세요.
            if (binding.signUpBirthYearEt.text.toString()
                    .isEmpty() || binding.signUpBirthMonthEt.text.toString().isEmpty()||binding.signUpBirthDayEt.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "생년월일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //2013: 성별을 입력해주세요.
            if (binding.signUpSexEt.text.toString().isEmpty()) {
                Toast.makeText(this, "성별이 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            //정보세팅
            var userId: String = binding.signUpIdEt.text.toString()
            var password : String = binding.signUpPwdEt.text.toString()
            var userName : String = binding.signUpNameEt.text.toString()
            var birth : String = binding.signUpBirthYearEt.text.toString() + "년" + binding.signUpBirthMonthEt.text.toString() + "월" + binding.signUpBirthDayEt.text.toString() + "일"
            var email:String = binding.signUpEmailEt.text.toString() + "@" + binding.signUpDirectInputEt.text.toString()
            var phoneNum : String = binding.signUpPhoneNumEt.text.toString()
            var sex : String = binding.signUpSexEt.text.toString()

            val intent = Intent(this,ProfileActivity::class.java)
            intent.putExtra("userId",userId)
            intent.putExtra("password",password)
            intent.putExtra("userName",userName)
            intent.putExtra("birth",birth)
            intent.putExtra("email",email)
            intent.putExtra("phoneNum",phoneNum)
            intent.putExtra("sex",sex)
            startActivity(intent)
            finish()

        }
    }

}