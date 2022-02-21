package com.example.HonbabSignal

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityUserInfoModifyBinding

class UserInfoModifyActivity : AppCompatActivity(){
    lateinit var binding : ActivityUserInfoModifyBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.userInfoModifyCompleteBtn.setOnClickListener{

            //레트로핏 객체를 선언함
            var retrofit = getRetorfit()

            //retrofit에 interface를 넣어줌
            var userInfoModifyService = retrofit.create(UserInfoModifyService::class.java)

            //밑으로 정보세팅해서 retrofit사용하면 됨

            //수정완료 버튼의 setOnclickListener안쪽
        }
    }
}