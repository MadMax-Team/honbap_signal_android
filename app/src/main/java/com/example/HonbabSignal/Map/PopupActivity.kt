package com.example.HonbabSignal.Map

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.example.HonbabSignal.databinding.ActivityPopupBinding
import com.example.HonbabSignal.getRetorfit

class PopupActivity : Activity(){
    lateinit var binding: ActivityPopupBinding
    lateinit var txtText:TextView

    var retrofit = getRetorfit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopupBinding.inflate(layoutInflater)

        //인텐트로 넘어온 signal json가져오기
        val signalInfo = intent.getStringExtra("signal")

        var dm = getApplicationContext().getResources().getDisplayMetrics();

        var width = (dm.widthPixels * 0.7); // Display 사이즈의 90%
        var height = (dm.heightPixels * 0.7); // Display 사이즈의 90%

        binding.popupNicknameTv.text = "name"
        binding.popupNoteTv.text = ""


        getWindow().getAttributes().width = width.toInt();
        getWindow().getAttributes().height = height.toInt();


        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.root);

        binding.popupBackBtn.setOnClickListener {
            val intent = Intent(this, MapListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }


        binding.popupSignalBtn.setOnClickListener{
            Toast.makeText(this@PopupActivity, "시그널이 보내졌습니다.", Toast.LENGTH_SHORT).show()
            retrofitSendSignal()

        }

        binding.popupDmBtn.setOnClickListener{
            Toast.makeText(this@PopupActivity, "DM이 보내졌습니다.", Toast.LENGTH_SHORT).show()
        }
    }

}

fun retrofitSendSignal(){

}