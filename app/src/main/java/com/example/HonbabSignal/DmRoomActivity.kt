package com.example.HonbabSignal

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityDmRoomBinding
import com.google.firebase.database.DatabaseReference
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.database.FirebaseDatabase
import android.os.Handler
import com.google.firebase.firestore.ListenerRegistration


class DmRoomActivity: AppCompatActivity() {
    private val fireDatabase = FirebaseDatabase.getInstance()
    lateinit var binding: ActivityDmRoomBinding
    private var destinationUid : String? = null
    private var uid : String? = null
    private lateinit var registration: ListenerRegistration    // 문서 수신
    private lateinit var dm_Send_Button: Button
    private lateinit var dm_Text : EditText
    private lateinit var mAdapter: DmRoomAdapter
    private lateinit var database: DatabaseReference

    //recyclerView
    var arrayList = arrayListOf<DmModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDmRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = fireDatabase.reference
        mAdapter = DmRoomAdapter(this,arrayList)
        Log.d("say","i'm in DB ROOM")
        //FirebaseApp.initializeApp(this);

        //메세지를 보낸 시간
        val time = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("MM월dd일 hh:mm")
        val curTime = dateFormat.format(Date(time)).toString()
        destinationUid = intent.getStringExtra("destinationUid")

        //destinationUid = intent.getStringExtra("destinationUid")
        uid = "고악2"

        //어댑터 선언
        binding.dmRoomRecyclerview.adapter = mAdapter

        dm_Text = binding.editText
        dm_Send_Button = binding.button

        Log.d("say","i'm in DB ROOM2")

        val enterTime = Date(System.currentTimeMillis())

        Handler().postDelayed({ println(uid)
            //메시지 받기
            val temp = database.child("users").child(destinationUid!!).child("destinationUser").child(uid!!).child("lastMessage").get()
            val item = DmModel("정아",temp.toString(),"example",null,null)
            mAdapter.addItem(item)
            dm_Text.text = null
        }, 1000L)

        dm_Send_Button.setOnClickListener {

            val item = DmModel("고악",dm_Text.text.toString(),"example",null,null)

            //내 uid
            item.uid = uid

            //상대방 uid
            item.destinationUid = destinationUid


            //유저네임 보내기
            //database.child("Room").child("userName").setValue()

            //메세지 보내기
            database.child("users").child(uid!!).child("destinationUser").child(destinationUid!!).child("lastPerson").setValue(uid)
            database.child("users").child(uid!!).child("destinationUser").child(destinationUid!!).child("lastMessage").setValue(dm_Text.text.toString())

            mAdapter.addItem(item)
            Log.d("say","click dm_Send_Button")
            dm_Text.setText("")

            Log.d("chatUidNull dest", "$destinationUid")


        }


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
//        val item = DmModel("고악",dm_Text.text.toString(),"example",getTime)
//
//        mAdapter.addItem(item)
//        Log.d("say","addItem")
//
//
//        //채팅 입력창 초기화
//        dm_Text.setText("")
}


// 채팅창이 공백일 경우 버튼 비활성화
//        binding.editText.addTextChangedListener { text ->
//            binding.button.isEnabled = text.toString() != ""
//      }