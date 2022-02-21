package com.example.HonbabSignal

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityUserInfoModifyBinding

class UserInfoModifyActivity : AppCompatActivity() {
    lateinit var binding : ActivityUserInfoModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}