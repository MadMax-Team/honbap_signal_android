package com.example.HonbabSignal.DM

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.R
import com.example.HonbabSignal.RetrofitSevices.SignalService
import com.example.HonbabSignal.databinding.FragmentDmListBinding
import com.example.HonbabSignal.dmRoomListResponse
import com.example.HonbabSignal.getRetorfit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DmListFragment : Fragment(){
    lateinit var binding: FragmentDmListBinding
    private var dmRoomListDatas = ArrayList<Friend>()
    private lateinit var dmRoomId : String
    private lateinit var jwt: String

    private fun getDmRoomList(jwt: String, dmRoomListDatas: ArrayList<Friend>){
        var retrofit = getRetorfit()
        var signalService = retrofit.create(SignalService::class.java)

        signalService.getDmRoomList(jwt)
            .enqueue((object: Callback<dmRoomListResponse>{
                override fun onResponse(
                    call: Call<dmRoomListResponse>,
                    response: Response<dmRoomListResponse>
                ) {
                    var resp = response.body()!!
                    when (resp.code){
                        1000 ->{
                            for (i in resp.result){
                                Log.d("i",i.roomId.toString())

                                dmRoomListDatas.apply{
                                    add(
                                        Friend("name",null,"last",i.roomId)
                                    )
                                }
                            }
                            Log.d("setDmRoomList","success")
                            setAdapter(dmRoomListDatas)
                        }
                    }
                }

                override fun onFailure(call: Call<dmRoomListResponse>, t: Throwable) {
                    Log.d("setDmRoomList","onFailure")
                }


            }))
    }

    private fun setAdapter(dmRoomListDatas: ArrayList<Friend>){
        // 더미데이터랑 adapter 연결
        val dmListAdapter = DmListAdapter(dmRoomListDatas)
        // 리사이클러뷰에 adapter 연결
        binding.dmListFragmentRecyclerview.adapter = dmListAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDmListBinding.inflate(inflater, container, false)

        val spf_jwt = this.getActivity()?.getSharedPreferences("jwt", Context.MODE_PRIVATE)
        jwt = spf_jwt?.getString("jwt", "").toString()
        Log.d("jwt", jwt)

       getDmRoomList(jwt,dmRoomListDatas)
        return binding.root
    }
}