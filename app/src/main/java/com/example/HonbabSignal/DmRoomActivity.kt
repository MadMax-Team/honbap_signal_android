package com.example.HonbabSignal

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityDmRoomBinding
import java.text.SimpleDateFormat
import java.util.*
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION
import com.google.firebase.database.*
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue


class DmRoomActivity: AppCompatActivity() {
    private val fireDatabase = FirebaseDatabase.getInstance()
    lateinit var binding: ActivityDmRoomBinding
    private var destinationUid : String? = null
    private var uid : String? = null
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


        //뒤로가기 버튼 클릭 시 dm list로 돌아감
        binding.dmRoomBackBtn.setOnClickListener{
            finish()
            overridePendingTransition(0,0)
        }
        //FirebaseApp.initializeApp(this);

        //메세지를 보낸 시간
        val time = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("MM월dd일 hh:mm")
        val curTime = dateFormat.format(Date(time)).toString()
        destinationUid = intent.getStringExtra("destinationUid")

        //destinationUid = intent.getStringExtra("destinationUid")
        uid = "고악1"

        //어댑터 선언
        binding.dmRoomRecyclerview.adapter = mAdapter

        dm_Text = binding.editText
        dm_Send_Button = binding.button

        Log.d("say","i'm in DB ROOM2")

        val enterTime = Date(System.currentTimeMillis())

        //메세지 보내기1

        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val post = dataSnapshot.getValue<DmModel>()

                val temp = dataSnapshot.child("users").child(destinationUid!!).child("destinationUser").child(uid!!).child("lastMessage").getValue()
                Log.d("dmroomzz",temp.toString())
                mAdapter.addItem(
                    DmModel("정아",temp.toString(),"example",uid,destinationUid)
                ) // adapter에 추가합니다.
                binding.dmRoomRecyclerview.scrollToPosition(arrayList.size -1)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(postListener)

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
            binding.dmRoomRecyclerview.scrollToPosition(arrayList.size -1)
            dm_Text.setText("")

        }
//recyclerView.scrollToPosition(comments.size() - 1)

    }

//        lastVisibleItemPosition =
//            (recyclerView.layoutManager as LinearLayoutManager)
//                .findLastCompletelyVisibleItemPosition()
}
