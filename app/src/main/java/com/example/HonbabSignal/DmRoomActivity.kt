package com.example.HonbabSignal

import android.nfc.Tag
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.HonbabSignal.SocketHandler.mSocket
import com.example.HonbabSignal.databinding.ActivityDmRoomBinding
import com.example.HonbabSignal.databinding.FragmentDmListBinding
import io.socket.client.Socket.EVENT_CONNECT
import io.socket.emitter.Emitter
import org.json.JSONException
import org.json.JSONObject
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*

class DmRoomActivity: AppCompatActivity() {
    lateinit var binding: ActivityDmRoomBinding
    private lateinit var dm_Send_Button: Button
    private lateinit var dm_Text : EditText

    //recyclerView
    var arrayList = arrayListOf<DmModel>()
    val mAdapter = DmRoomAdapter(this,arrayList)

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("say","i'm in DB ROOM")
        super.onCreate(savedInstanceState)
        binding = ActivityDmRoomBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_dm_room)

        //어댑터 선언
        binding.dmRoomRecyclerview.adapter = mAdapter



        dm_Send_Button = findViewById(R.id.button)
        dm_Text = findViewById(R.id.editText)

        //socket이랑 연결
        //SocketHandler.setSocket()
        //SocketHandler.establishConnection()

        Log.d("say","i'm in DB ROOM2")

        dm_Send_Button.setOnClickListener {
            sendMessage()
            Log.d("say","click dm_Send_Button")
        }
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
        mAdapter.notifyDataSetChanged()

        //데이터 보내기
        //mSocket.emit("say",dm_Text)

        //채팅 입력창 초기화
        dm_Text.setText("")
    }

}
