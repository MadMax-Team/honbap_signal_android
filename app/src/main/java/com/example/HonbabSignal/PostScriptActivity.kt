package com.example.HonbabSignal

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.example.HonbabSignal.databinding.ActivityPopupBinding
import com.example.HonbabSignal.databinding.ActivityPostScriptBinding


class PostScriptActivity: Activity() {
    lateinit var binding: ActivityPostScriptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostScriptBinding.inflate(layoutInflater)



        binding.postScriptGoodMannersCv.setOnClickListener{

        }
        binding.postScriptNiceMenuPickCv.setOnClickListener{

        }
        binding.postScriptGoodCommunicationMannersCv.setOnClickListener{

        }
        binding.postScriptNiceTimeCv.setOnClickListener{

        }



    }
}