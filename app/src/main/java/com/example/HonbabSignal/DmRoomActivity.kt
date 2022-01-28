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
import io.socket.client.Socket.EVENT_CONNECT
import io.socket.emitter.Emitter
import org.json.JSONException
import org.json.JSONObject
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*

class DmRoomActivity: AppCompatActivity() {
    //internal lateinit var perferences:SharedPreferences
    private lateinit var dm_Text : EditText
    private lateinit var dm_Send_Button: Button
    private lateinit var screen_name : TextView

    //리사이클러뷰
    var arrayList = arrayListOf<DmModel>()
    val mAdapter = DmRoomAdapter(this,arrayList)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

        Log.d("say","i'm in DB ROOM")
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_dm_room)
        //preferences = getSharedPreferences("USERSIGN", Context.MODE_PRIVATE)
        //var users : Array<String> = arrayOf()

        //어댑터 선언
        dm_recyclerview.adapter = mAdapter
        //레이아웃 매니저 선언
        val lm = LinearLayoutManager(this)
        chat_recyclerview.layoutManager = lm
        chat_recyclerview.setHasFixedSize(true)//아이템이 추가삭제될때 크기측면에서 오류 안나게 해줌

        val myName = intent.getIntExtra("name",0)
        //screen_name = findViewById(R.id.dm_room_test_name)
        //screen_name.setText(myName)

        dm_Send_Button = findViewById(R.id.dm_Send_Button)
        dm_Text = findViewById(R.id.dm_Text)


        //socket이랑 연결
        //SocketHandler.setSocket()
        //SocketHandler.establishConnection()

        Log.d("say","i'm in DB ROOM3")
        //dm list에서 정보가져옴
        //preferences = getSharedPerferences("USERSIGN", Context.MODE_PRIVATE)

        //어댑터 선언
        //dm_recyclerview.adapter = mAdapter
        //레이아웃 매니저 선언
        //val lm = LinearLayoutManager(this)
        //dm_recyclerview.layoutManager = lm
        //dm_recyclerview.setHasFixedSize(true)//아이템이 추가삭제될때 크기측면에서 오류 안나게 해줌

        //dm_Send_Button = findViewById(R.id.dm_room_sendbutton)
        //dm_Text = findViewById(R.id.dm_room_edittext)


        //사용할 socket잡기
        //val mSocket = SocketHandler.getSocket()


        dm_Send_Button.setOnClickListener {
            //아이템 추가 부분
            //sendMessage()
        }
    }

    fun sendMessage() {
        val now = System.currentTimeMillis()

        val date = Date(now)
        //나중에 바꿔줄것 밑의 yyyy-MM-dd는 그냥 20xx년 xx월 xx일만 나오게 하는 식
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val getTime = sdf.format(date)


        //example에는 원래는 이미지 url이 들어가야할 자리
        preferences.getString("name","")
        val item = DmModel("고악",dm_Text.text.toString(),"example")
        mAdapter.addItem(item)
        mAdapter.notifyDataSetChanged()



        //데이터 보내기
        //mSocket.emit("say",dm_Text)

        //채팅 입력창 초기화
        dm_Text.setText("")
    }


    //기존 채팅들 불러오기


}
        //데이터 수신
        // args[0]은 서버의 데이터입니다. //
        //데이터 유형에 따라 "as Int"를 변경합니다.
        // 예 "as String" 또는 아무 것도 쓰지 않습니다
        // 데이터 로깅은 선택 사항입니다.
//        mSocket.on("eventName") { args ->
//            if (args[0] != null) {
//                val counter = args[0] as Int
//                Log.i("I",counter.toString())
//                runOnUiThread {
//                    // 데이터를 받은 후 작업을 실행하는 곳입니다.
//                }
//            }
//        }
//    }
//
//}