package com.example.HonbabSignal

import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityDmRoomBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class DmRoomActivity: AppCompatActivity() {
    lateinit var binding: ActivityDmRoomBinding
    private lateinit var dm_Send_Button: Button
    private lateinit var dm_Text : EditText
    private lateinit var mAdapter: DmRoomAdapter
    private lateinit var database: DatabaseReference

    //recyclerView
    var arrayList = arrayListOf<DmModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = DmRoomAdapter(this,arrayList)
        Log.d("say","i'm in DB ROOM")

        FirebaseApp.initializeApp(this);
        val database = Firebase.database
        val myRef = database.getReference("message")


        binding = ActivityDmRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //어댑터 선언
        binding.dmRoomRecyclerview.adapter = mAdapter



        dm_Send_Button = findViewById(R.id.button)
        dm_Text = findViewById(R.id.editText)

        Log.d("say","i'm in DB ROOM2")

        dm_Send_Button.setOnClickListener {
            sendMessage()
            Log.d("say","click dm_Send_Button")
        }

//        lastVisibleItemPosition =
//            (recyclerView.layoutManager as LinearLayoutManager)
//                .findLastCompletelyVisibleItemPosition()
    }

    fun sendMessage() {
        val now = System.currentTimeMillis()
        val date = Date(now)
        //나중에 바꿔줄것 밑의 yyyy-MM-dd는 그냥 20xx년 xx월 xx일만 나오게 하는 식
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val getTime = sdf.format(date)

        //example에는 원래는 이미지 url이 들어가야할 자리
        //preferences.getString("name","")
        val item = DmModel("고악",dm_Text.text.toString(),"example",getTime)
        mAdapter.addItem(item)
        Log.d("say","addItem")


        //채팅 입력창 초기화
        dm_Text.setText("")
    }
}
