package com.example.HonbabSignal

import android.app.Activity
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

}