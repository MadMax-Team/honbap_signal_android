package com.example.HonbabSignal

import android.R.attr
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
import com.google.android.gms.tasks.OnSuccessListener
import android.R.attr.button
import android.content.ContentValues.TAG
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore


class DmRoomActivity: AppCompatActivity() {
    private val fireDatabase = FirebaseDatabase.getInstance().reference
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

        mAdapter = DmRoomAdapter(this,arrayList)
        Log.d("say","i'm in DB ROOM")
        FirebaseApp.initializeApp(this);

        //메세지를 보낸 시간
         val time = System.currentTimeMillis()
         val dateFormat = SimpleDateFormat("MM월dd일 hh:mm")
         val curTime = dateFormat.format(Date(time)).toString()
         destinationUid = intent.getStringExtra("destinationUid")
         uid = Firebase.auth.currentUser?.uid.toString()

        //어댑터 선언
        binding.dmRoomRecyclerview.adapter = mAdapter

        dm_Text = binding.editText
        dm_Send_Button = binding.button

        Log.d("say","i'm in DB ROOM2")

       val enterTime = Date(System.currentTimeMillis())


        dm_Send_Button.setOnClickListener {

            val item = DmModel("고악",dm_Text.text.toString(),"example",null,null)

            //내 uid
            item.uid = Firebase.auth.currentUser?.uid.toString()

            //상대방 uid
            item.destinationUid = Firebase.auth.currentUser?.uid.toString()


            //database.child("users").setValue(dm_Text.text.toString())

            //유저네임 보내기
            //database.child("Room").child("userName").setValue()
            //메세지 보내기
            database.child("Room").child("message").setValue(dm_Text.text.toString())

            Handler().postDelayed({ println(uid)
                fireDatabase.child("chatrooms").child(uid.toString()).child("comments").push().setValue(dm_Text)
                dm_Text.text = null }, 1000L)
            Log.d("chatUidNull dest", "$destinationUid")


            mAdapter.addItem(item)
            Log.d("say","click dm_Send_Button")
            dm_Text.setText("")
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

//        val user = hashMapOf(
//            "first" to "Ada",
//            "last" to "Lovelace",
//            "born" to 1815
//        )
//
//        fireDatabase.collection("users").add(user)
//            .addOnSuccessListener {
//                binding.editText.text.clear()
//
//            }
//            .addOnFailureListener { e ->
//                Toast.makeText(context, "전송하는데 실패했습니다", Toast.LENGTH_SHORT).show()
//                Log.w("ChatFragment", "Error occurs: $e")
//            }

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


