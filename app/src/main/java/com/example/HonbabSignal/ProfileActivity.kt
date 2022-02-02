package com.example.HonbabSignal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    private val OPEN_GALLERY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //사진찾기 버튼을 눌렀을때 갤러리로 들어가서 사진을 클릭하면 그걸 가지고 이미지 뷰로 옵니다.
        binding.profileOpenGalleryBtn.setOnClickListener()
}