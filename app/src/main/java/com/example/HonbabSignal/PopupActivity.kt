package com.example.HonbabSignal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import com.example.HonbabSignal.databinding.ActivityLogInBinding
import com.example.HonbabSignal.databinding.ActivityPopupBinding

class PopupActivity : Activity(){
    lateinit var binding: ActivityPopupBinding
    lateinit var txtText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopupBinding.inflate(layoutInflater)
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.root);

        binding.popupBackBtn.setOnClickListener {
            val intent = Intent(this,MapListActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }
    }

}