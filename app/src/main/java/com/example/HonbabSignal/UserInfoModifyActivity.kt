package com.example.HonbabSignal

import android.app.AlertDialog
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityUserInfoModifyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoModifyActivity : AppCompatActivity(){
    lateinit var binding : ActivityUserInfoModifyBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //레트로핏 객체를 선언함
        var retrofit = getRetorfit()

        //retrofit에 interface를 넣어줌
        var userInfoModifyService = retrofit.create(UserInfoModifyService::class.java)
        var userIdx = 1
        //유저의 정보를 받아오는부분
        userInfoModifyService.getUserInfo(userIdx).enqueue(object : Callback<UserInfoAuthResponse>{
            override fun onResponse(
                call: Call<UserInfoAuthResponse>,
                response: Response<UserInfoAuthResponse>
            ) {
                var respUserInfo = response.body()!!
                Log.d("유저개인정보 서버로부터 온 코드", respUserInfo.code.toString())
                when (respUserInfo.code) {
                    1000 -> {
                        binding.userInfoModifyIdTv.text = respUserInfo.result.userId
                        binding.userInfoModifyNameEt.hint = respUserInfo.result.userName
                        var birthYear = respUserInfo.result.birth.slice(IntRange(0,3))
                        var birthMonth = respUserInfo.result.birth.slice(IntRange(5,6))
                        var birthDay = respUserInfo.result.birth.slice(IntRange(8,9))
                        binding.userInfoModifyBirthYearEt.hint = birthYear
                        binding.userInfoModifyBirthMonthEt.hint = birthMonth
                        binding.userInfoModifyBirthDayEt.hint = birthDay
                        binding.userInfoModifyEmailTv.text = respUserInfo.result.email
                        binding.userInfoModifyNumberTv.text = respUserInfo.result.phoneNum
                        binding.userInfoModifySexTv.text = respUserInfo.result.sex
                        binding.userInfoModifyLastUpdateTv.text = respUserInfo.result.updateAt
                        binding.userInfoModifyCreateDateTv.text = respUserInfo.result.createAt
                    }
                    else -> {
                        Toast.makeText(this@UserInfoModifyActivity, "서버에서 개인정보 불러오기가 실패하였습니다.", Toast.LENGTH_SHORT).show()

                    }


                }
            }

            override fun onFailure(call: Call<UserInfoAuthResponse>, t: Throwable) {
                Log.d("DEBUGInSignUp", t.message.toString())
                var dialog = AlertDialog.Builder(this@UserInfoModifyActivity)

                dialog.setTitle("실패!")
                dialog.setMessage("통신에 실패했습니다!")
                dialog.show()
            }

        })
        binding.userInfoModifyCompleteBtn.setOnClickListener{

            //수정정보 세팅
            var name = binding.userInfoModifyNameEt.text.toString()
            var birth = binding.userInfoModifyBirthYearEt.text.toString() + "년" + binding.userInfoModifyBirthMonthEt.text.toString() + "월" + binding.userInfoModifyBirthDayEt.text.toString() + "일"

            userInfoModifyService.patchUserInfo(userIdx,name,birth).enqueue(object : Callback<UserInfoPatchResponse>{
                override fun onResponse(
                    call: Call<UserInfoPatchResponse>,
                    response: Response<UserInfoPatchResponse>
                ) {
                    var respUserPatch = response.body()!!
                    Log.d("패치과정에서 온 코드",respUserPatch.code.toString())
                    when(respUserPatch.code){
                        1000->{
                            Toast.makeText(this@UserInfoModifyActivity, "개인 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        else->{
                            Toast.makeText(this@UserInfoModifyActivity, "서버에서 개인정보 수정이 실패하였습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<UserInfoPatchResponse>, t: Throwable) {
                    Log.d("DEBUGInSignUp", t.message.toString())
                    var dialog = AlertDialog.Builder(this@UserInfoModifyActivity)

                    dialog.setTitle("실패!")
                    dialog.setMessage("통신에 실패했습니다!")
                    dialog.show()
                }

            })



            //수정완료 버튼의 setOnclickListener안쪽
        }
    }
}