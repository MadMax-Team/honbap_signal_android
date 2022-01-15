package com.example.HonbabSignal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityLogInBinding
import com.example.HonbabSignal.databinding.ActivityMainBinding

class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}