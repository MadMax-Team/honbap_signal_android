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

        binding
        //사진찾기 버튼을 눌렀을때 갤러리로 들어가서 사진을 클릭하면 그걸 가지고 이미지 뷰로 옵니다.
        binding.profileOpenGalleryBtn.setOnClickListener{loadImage()}

        if(intent.hasExtra("userId")||intent.hasExtra("password")||intent.hasExtra("userName")||
            intent.hasExtra("birth")||intent.hasExtra("userId")||intent.hasExtra("email")||
            intent.hasExtra("phoneNum")||intent.hasExtra("sex")){
            binding.profileSignUpBtnTv.setOnClickListener{
                //retrofit 개체 생성
                var retrofit = Retrofit.Builder()
                    .baseUrl("http://52.78.100.231:3001")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                //retrofit에 interface를 넣어줌
                var signUpService = retrofit.create(SignUpService::class.java)
                //밑으로 retrofit사용

                //정보세팅
                var userId: String = intent.hasExtra("userId").toString()
                var password : String = intent.hasExtra("password").toString()
                var userName : String = intent.hasExtra("userName").toString()
                var birth : String = intent.hasExtra("birth").toString()
                var email:String = intent.hasExtra("email").toString()
                var phoneNum : String = intent.hasExtra("phoneNum").toString()
                var sex : String = intent.hasExtra("sex").toString()

                binding.profileLoadingPb.visibility = View.VISIBLE
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
                        var resp = responseSignUp.body()!!

                        when(resp.code){
                            1000-> {binding.profileLoadingPb.visibility = View.GONE

                                }
                            else -> {onSignUpFailure(resp.code, resp.message)
                                        finish()}
                        }

                    }
                    //서버와의 통신에 실패했을때
                    override fun onFailure(call: Call<SignUpAuthResponse>, t: Throwable) {
                        Log.d("DEBUG", t.message.toString())
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
        }
    }

}