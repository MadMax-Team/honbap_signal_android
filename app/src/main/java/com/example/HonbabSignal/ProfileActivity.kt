package com.example.HonbabSignal

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityProfileBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    private val OPEN_GALLERY = 1
    val Gallery = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //사진찾기 버튼을 눌렀을때 갤러리로 들어가서 사진을 클릭하면 그걸 가지고 이미지 뷰로 옵니다.
        binding.profileOpenGalleryBtn.setOnClickListener{loadImage()}

        //intent(회원가입정보 1 똑바로 넘어왔을경우)
        if(intent.hasExtra("userId")&&intent.hasExtra("password")&&intent.hasExtra("userName")&&
            intent.hasExtra("birth")&&intent.hasExtra("email")&&
            intent.hasExtra("phoneNum")&&intent.hasExtra("sex")){
            binding.profileSignUpBtnTv.setOnClickListener{
                //retrofit 개체 생성
                var retrofit = Retrofit.Builder()
                    .baseUrl("http://52.78.100.231:3001")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                //retrofit에 interface를 넣어줌
                var signUpService = retrofit.create(SignUpService::class.java)
                //밑으로 retrofit사용

                //정보세팅(회원정보)
                var userId : String = intent.getStringExtra("userId")!!
                var password : String = intent.getStringExtra("password")!!
                var userName : String = intent.getStringExtra("userName")!!
                var birth : String = intent.getStringExtra("birth")!!
                var email:String = intent.getStringExtra("email")!!
                var phoneNum : String = intent.getStringExtra("phoneNum")!!
                var sex : String = intent.getStringExtra("sex")!!

                binding.profileLoadingPb.visibility = View.VISIBLE
                //signUp (POST)실행
                signUpService.signUp(
                    userId,
                    password,
                    userName,
                    birth,
                    email,
                    phoneNum,
                    sex
                ).enqueue(object: Callback<SignUpAuthResponse> {

                    //서버와의 통신에 성공했을때(응답값을 받아왔을때) 실행되는 코드
                    override fun onResponse(call: Call<SignUpAuthResponse>, responseSignUp: Response<SignUpAuthResponse>) {
                        var respSign = responseSignUp.body()!!
                        Log.d("SignUpcode",respSign.code.toString())
                        when (respSign.code) {
                            1000 -> {
                                Log.d("signUp:", "signup1 success")
                                //signUp(POST)가 성공했을때
                                //user의 index를 받아오는 GET실행
                                var userId : String = intent.getStringExtra("userId")!!
                                Log.d("GET들어가기전 userId",userId)
                                signUpService.getUserIdx(userId)
                                    .enqueue(object : Callback<UserIdxAuthResponse> {
                                        override fun onResponse(
                                            call: Call<UserIdxAuthResponse>,
                                            response: Response<UserIdxAuthResponse>
                                        ) {
                                            var respIdx = response.body()!!
                                            Log.d("GET관련 code",respIdx.code.toString())
                                            when (respIdx.code) {
                                                1000 -> {
                                                    //userIdx 세팅
                                                    var userIdx = respIdx.result[0].userIdx
                                                    Log.d("현재 userIdx값",userIdx.toString())
                                                    Log.d("getUserIdx:", "get user index success")
                                                    //userIdx받아오는 GET성공했을때
                                                    //profile 정보 서버에 업로드(POST)

                                                    //정보세팅(프로필, userIdx제외한 나머지 정보)
                                                    var nickName: String = binding.profileNickNameEt.text.toString()
                                                    var profileImg: String = binding.profileProfileImgIv?.toString()
                                                    var taste: String = binding.profileTasteEt?.text.toString()
                                                    var hateFood :String = binding.profileHateFoodEt?.text.toString()
                                                    var interest : String = binding.profileInterestEt?.text.toString()
                                                    var avgSpeed : String = binding.profileAvgSpeedEt?.text.toString()
                                                    var preferArea : String = binding.profilePreferAreaEt?.text.toString()
                                                    var mbti : String = binding.profileMbtiEt?.text.toString()
                                                    var userIntroduce = binding.profileUserIntroduceEt?.text.toString()
                                                    Log.d("정보세팅은 끝","밑으로 프로필 업 레트로핏 실행 +현재 nickName : "+ nickName)

                                                    signUpService.profileUp(
                                                        userIdx,
                                                        nickName,
                                                        profileImg,
                                                        taste,
                                                        hateFood,
                                                        interest,
                                                        avgSpeed,
                                                        preferArea,
                                                        mbti,
                                                        userIntroduce
                                                    ).enqueue(object : Callback<SignUpAuthResponse> {
                                                        override fun onResponse(call: Call<SignUpAuthResponse>, response: Response<SignUpAuthResponse>) {
                                                            var respProfile = response.body()!!
                                                            Log.d("프로필통신을 통해서 온 코드",respProfile.code.toString())
                                                            when (respProfile.code) {
                                                                1000 -> {
                                                                    Log.d(
                                                                        "profileUp:",
                                                                        "profileUp success"
                                                                    )
                                                                    binding.profileLoadingPb.visibility =
                                                                        View.GONE
                                                                    finish()
                                                                }
                                                                else -> {onSignUpFailure(respSign.code, respSign.message)
                                                                }

                                                            }
                                                        }

                                                        override fun onFailure(
                                                            call: Call<SignUpAuthResponse>,
                                                            t: Throwable
                                                        ) {
                                                            Log.d(
                                                                "DEBUGInprofileUp",
                                                                t.message.toString()
                                                            )
                                                            var dialog =
                                                                AlertDialog.Builder(this@ProfileActivity)

                                                            dialog.setTitle("실패!")
                                                            dialog.setMessage("프로필업 통신에 실패했습니다!")
                                                            dialog.show()
                                                            binding.profileLoadingPb.visibility =
                                                                View.GONE
                                                        }

                                                    })
                                                }
                                            }
                                        }

                                        override fun onFailure(
                                            call: Call<UserIdxAuthResponse>,
                                            t: Throwable
                                        ) {
                                            Log.d("DEBUGInGetUserIdx", t.message.toString())
                                            var dialog = AlertDialog.Builder(this@ProfileActivity)

                                            dialog.setTitle("실패!")
                                            dialog.setMessage("통신에 실패했습니다!")
                                            dialog.show()
                                            binding.profileLoadingPb.visibility = View.GONE
                                        }


                                    })
                            }
                            else -> {
                                onSignUpFailure(respSign.code, respSign.message)
                                finish()
                            }
                        }
                    }
                    //서버와의 통신에 실패했을때
                    override fun onFailure(call: Call<SignUpAuthResponse>, t: Throwable) {
                        Log.d("DEBUGInSignUp", t.message.toString())
                        var dialog = AlertDialog.Builder(this@ProfileActivity)

                        dialog.setTitle("실패!")
                        dialog.setMessage("통신에 실패했습니다!")
                        dialog.show()
                        binding.profileLoadingPb.visibility = View.GONE
                    }

                })
            }
        }
    }

    private fun loadImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Load Picture"), Gallery)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Gallery){
            if(resultCode == Gallery){
                if(resultCode == RESULT_OK){
                    var dataUri = data?.data
                    try{
                        var bitmap : Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
                        binding.profileProfileImgIv.setImageBitmap(bitmap)
                        Log.d("act","activate!!")
                    }catch(e:Exception){
                        Toast.makeText(this,"$e", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    //something Wrong
                }
            }
        }
    }

    fun onSignUpFailure(code: Int, message: String) {
        binding.profileLoadingPb.visibility = View.GONE

        when (code) {
            2001, 2002, 3001 -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                return
            }
            2005, 2006, 2007, 3003 -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                return
            }
            2008, 2009, 3004 -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                return
            }
            2003, 2004, 3002, 4000 ->{
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                return
            }
        }
    }
}