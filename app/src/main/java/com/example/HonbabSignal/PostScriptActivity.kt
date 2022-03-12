package com.example.HonbabSignal

import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import com.example.HonbabSignal.databinding.ActivityPopupBinding
import com.example.HonbabSignal.databinding.ActivityPostScriptBinding


class PostScriptActivity: Activity() {
    lateinit var binding: ActivityPostScriptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostScriptBinding.inflate(layoutInflater)

        var dm = getApplicationContext().getResources().getDisplayMetrics()

        var width = (dm.widthPixels * 0.9) // Display 사이즈의 90%
        var height = (dm.heightPixels * 0.8) // Display 사이즈의 90%

        getWindow().getAttributes().width = width.toInt()
        getWindow().getAttributes().height = height.toInt()


        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)


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