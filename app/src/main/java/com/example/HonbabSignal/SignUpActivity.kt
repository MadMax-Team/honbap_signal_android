package com.example.HonbabSignal

import android.content.Intent
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

        binding.signUpNextBtnTv.setOnClickListener {

            //비어있는거 체크
            if (binding.signUpIdEt.text.toString().isEmpty()) {
                Toast.makeText(this, "아이디가 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
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
            if (binding.signUpBirthYearEt.text.toString()
                    .isEmpty() || binding.signUpBirthMonthEt.text.toString().isEmpty()||binding.signUpBirthDayEt.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "생년월일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
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

        }
    }

}