package com.example.HonbabSignal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.databinding.FragmentDmListBinding

class DmListFragment : Fragment(){
    lateinit var binding: FragmentDmListBinding
    private var friendDatas = ArrayList<Friend>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDmListBinding.inflate(inflater, container, false)


        //데이터 리스트 생성 (서버 없어서 해봄)
        friendDatas.apply{
            add(Friend("고악",R.drawable.kakao_default_profile_image,"언제 끝나?",null))
            add(Friend("정아",R.drawable.kakao_default_profile_image,"뭐해",null))
        }


        // 더미데이터랑 adapter 연결
        val dmListAdapter = DmListAdapter(friendDatas)
        // 리사이클러뷰에 adapter 연결
        binding.dmListFragmentRecyclerview.adapter = dmListAdapter

        //리스너 객체 생성 및 전달
        dmListAdapter.setMytemClickListener(object : DmListAdapter.MyitemClickListener{
            override fun onItemClick(friend: Friend) {
                Log.d("say","I will")
                val intent = Intent(activity, DmRoomActivity::class.java)

                Log.d("say",friend.name.toString())
                //intent.putExtra("name",friend.name)
                startActivity(intent)
            }

        })

        // 레이아웃 매니저 설정
        //binding.dmListFragmentRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        return binding.root
    }
}