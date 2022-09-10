package com.example.HonbabSignal.Map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.HonbabSignal.*
import com.example.HonbabSignal.RetrofitSevices.MapService
import com.example.HonbabSignal.databinding.ActivityMapListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MapListActivity :AppCompatActivity() {
    lateinit var binding: ActivityMapListBinding
    var mapSignalListDatas = ArrayList<MapSignal>()
    lateinit var jwt: String
    var retrofit = getRetorfit()
    var MapService = retrofit.create(MapService::class.java)

    private fun getList(jwt: String, mapSignalListDatas: ArrayList<MapSignal>) {

        MapService.getSignalInfo(jwt)
            .enqueue(object : Callback<SignalInfoAuthResponse> {
                override fun onResponse(
                    call: Call<SignalInfoAuthResponse>,
                    response: Response<SignalInfoAuthResponse>
                ) {
                    var respIdx = response.body()!!
                    Log.d("editingProfile_code", respIdx.code.toString())
                    when (respIdx.code) {
                        1000 -> {

                            Log.d("MapListActivity", respIdx.result.toString())
                            for (i in respIdx.result) {
                                Log.d("i", i.nickName.toString())

                                mapSignalListDatas.apply {
                                    add(
                                        MapSignal(
                                            i.checkSigWrite,
                                            i.nickName.toString(),
                                            i.taste,
                                            i.hateFood,
                                            i.interest,
                                            i.avgSpeed,
                                            i.preferArea,
                                            i.mbti,
                                            i.userIntroduce,
                                            i.signalIdx,
                                            i.userIdx,
                                            i.sigPromiseTime,
                                            i.sigPromiseArea
                                        )
                                    )
                                }

                                Log.d("mapSignal", mapSignalListDatas.toString())

                            }

                            Log.d("mapSignal", "for문 끝남")
                            setAdapter(mapSignalListDatas)

                        }
                        4000 -> {
                            Log.d("MapListActivity", "데이터베이스 에러")
                        }
                    }
                }

                override fun onFailure(call: Call<SignalInfoAuthResponse>, t: Throwable) {
                    Log.d("MapListActivity", "실패")
                }


            })

    }

    private fun setAdapter(mapSignalListDatas: ArrayList<MapSignal>){
        //리사이클러뷰 어뎁터 세팅
        val mapSignalListRVAdapter = MapSignalListRVAdapter(mapSignalListDatas)
        binding.mapSignalListRecyclerviewGrid.adapter = mapSignalListRVAdapter
        binding.mapSignalListRecyclerviewGrid.layoutManager = GridLayoutManager(this@MapListActivity, 2)

        //리사이클러뷰 클릭관련 함수정의
        mapSignalListRVAdapter.setMyItemClickListener(object :
            MapSignalListRVAdapter.MyItemClickListner {
            override fun onItemClick(signal: MapSignal) {
                var intent = Intent(this@MapListActivity, PopupActivity::class.java)

                intent.putExtra("jwt",jwt)
                intent.putExtra("nickName",signal.nickName)
                intent.putExtra("userIntroduce",signal.userIntroduce)
                intent.putExtra("avgSpeed",signal.avgSpeed)
                intent.putExtra("hateFood",signal.hateFood)
                intent.putExtra("signalIdx",signal.signalIdx)
                intent.putExtra("userIdx",signal.userIdx)
                intent.putExtra("taste",signal.taste)
                intent.putExtra("sigPromiseArea",signal.sigPromiseArea)
                intent.putExtra("sigPromiseTime",signal.sigPromiseTime)
                intent.putExtra("checkSigWrite",signal.checkSigWrite)
                intent.putExtra("mbti", signal.mbti)
                intent.putExtra("preferArea",signal.preferArea)


                startActivity(intent)
            }
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //jwt
        val spf_jwt = this.getSharedPreferences("jwt", Context.MODE_PRIVATE)
        jwt = spf_jwt?.getString("jwt", "").toString()
        Log.d("jwt", jwt)

        binding.mapListBackBtn.setOnClickListener {
            finish()
            overridePendingTransition(0, 0)
        }

        getList(jwt, mapSignalListDatas)


    }
}

