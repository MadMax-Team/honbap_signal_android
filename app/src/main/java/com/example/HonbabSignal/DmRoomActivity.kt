package com.example.HonbabSignal

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityDmRoomBinding
import java.text.SimpleDateFormat
import java.util.*


class DmRoomActivity: AppCompatActivity() {
    lateinit var binding: ActivityDmRoomBinding
    private var destinationUid : String? = null
    private var uid : String? = null
    private lateinit var dm_Send_Button: Button
    private lateinit var dm_Text : EditText
    private lateinit var mAdapter: DmRoomAdapter
    private lateinit var destinationNickname: String

    //recyclerView
    var arrayList = arrayListOf<DmModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDmRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)


        destinationNickname = intent.getStringExtra("destinationNickname").toString()
        binding.dmRoomDestinationNickname.text = destinationNickname


//        database = fireDatabase.reference
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
        uid = "도동"

        //어댑터 선언
        binding.dmRoomRecyclerview.adapter = mAdapter

        dm_Text = binding.editText
        dm_Send_Button = binding.button

        Log.d("say","i'm in DB ROOM2")

        val enterTime = Date(System.currentTimeMillis())


        dm_Send_Button.setOnClickListener {

            val item = DmModel("고악",dm_Text.text.toString(),"example",null,null)
            //내 uid
            item.uid = uid
            //상대방 uid
            item.destinationUid = destinationUid

            mAdapter.addItem(item)
            binding.dmRoomRecyclerview.scrollToPosition(arrayList.size -1)
            dm_Text.setText("")

        }

    }

}
