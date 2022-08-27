package com.example.HonbabSignal.Map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.HonbabSignal.*
import com.example.HonbabSignal.RetrofitSevices.MapService
import com.example.HonbabSignal.adapter.MapSignalListRVAdapter
import com.example.HonbabSignal.databinding.ActivityMapListBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
private var mapSignalListDatas = ArrayList<MapSignal>()

class MapListActivity :AppCompatActivity(){

    lateinit var binding: ActivityMapListBinding
    lateinit var myIdx: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spf_jwt = this.getSharedPreferences("jwt", Context.MODE_PRIVATE)
        val jwt: String = spf_jwt?.getString("jwt","").toString()
        Log.d("jwt",jwt)

        binding.mapListBackBtn.setOnClickListener {
            finish()
            overridePendingTransition(0,0)
        }


        //임시 데이터리스트 생성(서버 없어서 해봄)


        getList(jwt)
        Log.d("MapListActivity_m",mapSignalListDatas.toString())
//        220dab12c567826e67c2895b00ea7ff525a1bc07

        //리사이클러뷰 어뎁터 세팅
        val mapSignalListRVAdapter = MapSignalListRVAdapter(mapSignalListDatas)
        binding.mapSignalListRecyclerviewGrid.adapter = mapSignalListRVAdapter
        binding.mapSignalListRecyclerviewGrid.layoutManager = GridLayoutManager(this,2)

        //리사이클러뷰 클릭관련 함수정의
        mapSignalListRVAdapter.setMyItemClickListener(object : MapSignalListRVAdapter.MyItemClickListner{
            override fun onItemClick(signal : MapSignal) {
            var intent = Intent(this@MapListActivity, PopupActivity::class.java)
            val gson = Gson()
            val signalJson = gson.toJson(signal)
            intent.putExtra("signal", signalJson)
            startActivity(intent)
            }
        })

//        //recyclerView
//        Log.d("say","make recyclerview")
//        var listManager = GridLayoutManager(this,2)
//        var listAdapter = ListAdapterGrid(mapSignalListDatas)
//        //val mapSignalListAdapter = MapSignalListAdapter(mapSignalListDatas)
//
//        var recyclerList = recyclerGridView.apply {
//            setHasFixedSize(true)
//            layoutManager = listManager
//            adapter = listAdapter
//        }
    }



    private fun getList(jwt: String){
        var retrofit = getRetorfit()
        var MapService = retrofit.create(MapService::class.java)

        MapService.getSignalInfo(jwt)
            .enqueue(object: Callback<SignalInfoAuthResponse>{
                override fun onResponse(
                    call: Call<SignalInfoAuthResponse>,
                    response: Response<SignalInfoAuthResponse>
                ) {
                    var respIdx = response.body()!!
                    Log.d("editingProfile_code", respIdx.code.toString())
                    when (respIdx.code) {
                        1000 -> {
                            var signalUserIdxList = respIdx.result

                            Log.d("MapListActivity", respIdx.result.toString())
                            for (i in respIdx.result){
                                Log.d("i", i.toString())


                                mapSignalListDatas.add(
                                        MapSignal(i.checkSigWrite, i.nickName, i.taste, i.hateFood,
                                            i.interest, i.avgSpeed, i.preferArea, i.mbti, i.userIntroduce,
                                            i.signalIdx, i.userIdx, i.sigPromiseTime, i.sigPromiseArea
                                        )
                                    )

                            }

                        }
                        4000 -> {
                            Log.d("MapListActivity","데이터베이스 에러")
                        }
                    }
                }

                override fun onFailure(call: Call<SignalInfoAuthResponse>, t: Throwable) {
                    Log.d("MapListActivity","실패")
                }


            })

    }

}