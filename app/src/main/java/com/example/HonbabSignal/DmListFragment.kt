package com.example.HonbabSignal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.databinding.FragmentDmListBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import android.app.NotificationManager

class DmListFragment : Fragment(){
    lateinit var binding: FragmentDmListBinding
    private var friendDatas = ArrayList<Friend>()
    private lateinit var database: DatabaseReference
    private val fireDatabase = FirebaseDatabase.getInstance()
    private lateinit var destinationNickname : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDmListBinding.inflate(inflater, container, false)

        //**************************************************************************
        //데이터 리스트 생성 (서버 없어서 해봄)
        //원래는 서버에서 받아오는 것들임
        friendDatas.apply{
            add(Friend("고악",R.drawable.goak_profile,"상대방에게 인사해보세요~!",null))
            add(Friend("도동",R.drawable.dodong_profile,"상대방에게 인사해보세요~!",null))
            add(Friend("곤",R.drawable.geon_profile,"상대방에게 인사해보세요~!",null))
        }

        database = fireDatabase.reference

        // 더미데이터랑 adapter 연결
        val dmListAdapter = DmListAdapter(friendDatas)
        // 리사이클러뷰에 adapter 연결
        binding.dmListFragmentRecyclerview.adapter = dmListAdapter

        //******************************************************************************
        //처음 DmRoom 생성하는 경우
        //나에게 생성되는 uid 하위목록에 목적지(채팅하는 사람)uid가 있는지 확인 -> 없으면 방만듦
        val destinationUid = "고악"
        val uid = "코뿡"

//        testBtn.setOnClickListener(){
//            //서버에서 채팅창 있는지 확인해줌
//            //없으면 생성
//            //푸시알림 만들기
//            //생성 시 서버한테 뭐 생성했다 이런거 보내주고(?)
//            //firebase child생성 추가 (근데 처음에 내 child항목 생성 미리 해줘야할듯?..아닌가 해봐야알듯)
//            database.child("users").child(uid).child("destinationUser").child(destinationUid).child("lastPerson").setValue(uid)
//            database.child("users").child(uid).child("destinationUser").child(destinationUid).child("lastMessage").setValue("처음 생성된 채팅방입니다.")
//        }

        //리스너 객체 생성 및 전달
        dmListAdapter.setMytemClickListener(object : DmListAdapter.MyitemClickListener{
            override fun onItemClick(friend: Friend) {
                Log.d("say","I will")
                destinationNickname = friend.name.toString()

                val intent = Intent(activity, DmRoomActivity::class.java)
                intent.putExtra("destinationNickname",destinationNickname)
                //DmRoom으로 넘어갈 때 같이 가는 정보 (내 uid, 상대방 uid)
                intent.putExtra("destinationUid", destinationUid)

                Log.d("say",friend.name.toString())
                //intent.putExtra("name",friend.name)
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent)
            }
        })

        fun makeDmRoom(){
            val intent = Intent(activity, DmRoomActivity::class.java)
        }

        return binding.root
    }
}