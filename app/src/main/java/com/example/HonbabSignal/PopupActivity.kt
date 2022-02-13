package com.example.HonbabSignal

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.example.HonbabSignal.databinding.ActivityLogInBinding
import com.example.HonbabSignal.databinding.ActivityPopupBinding

class PopupActivity : Activity(){
    lateinit var binding: ActivityPopupBinding
    lateinit var txtText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopupBinding.inflate(layoutInflater)


        var dm = getApplicationContext().getResources().getDisplayMetrics();

        var width = (dm.widthPixels * 0.7); // Display 사이즈의 90%
        var height = (dm.heightPixels * 0.7); // Display 사이즈의 90%

        getWindow().getAttributes().width = width.toInt();

        getWindow().getAttributes().height = height.toInt();


        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.root);

        binding.popupBackBtn.setOnClickListener {
            val intent = Intent(this,MapListActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }

        binding.popupSignalBtn.setOnClickListener{
            Toast.makeText(this@PopupActivity, "signal이 보내졌습니다.", Toast.LENGTH_SHORT).show()
        }

        binding.popupDmBtn.setOnClickListener{
            Toast.makeText(this@PopupActivity, "Dm이 보내졌습니다.", Toast.LENGTH_SHORT).show()
        }
    }

}