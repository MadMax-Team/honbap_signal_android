package com.example.HonbabSignal

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.HonbabSignal.RetrofitSevices.MapService
import com.example.HonbabSignal.adapter.MapSignalListRVAdapter
import com.example.HonbabSignal.databinding.ActivityMapListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapListActivity:AppCompatActivity() {

    lateinit var binding: ActivityMapListBinding

    private var mapSignalListDatas = ArrayList<MapSignal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mapListBackBtn.setOnClickListener {
            finish()
            overridePendingTransition(0,0)
        }


        //임시 데이터리스트 생성(서버 없어서 해봄)
//        mapSignalListDatas.apply{
//            add(MapSignal(SignalMode.DEFAULT,true,"곤",R.drawable.geon_profile,"나와 111m","4분전","일식","돈까스","젤리","20대","초콜릿"))
//            add(MapSignal(SignalMode.CUSTOM,false,"코뿡",R.drawable.cobboong,"나와 222m","1분전","헤비토커","초밥","고기","샤브샤브","사이다"))
//            add(MapSignal(SignalMode.DEFAULT,true, "데옹",R.drawable.deong,"나와 333m","6분전","푸드파이터","떡볶이","국밥","고기","풀"))
//            add(MapSignal(SignalMode.DEFAULT ,false, "도동",R.drawable.dodong_profile,"나와 15m","10분전","디자인","맥주","피자","20대","고기"))
//            add(MapSignal(SignalMode.CUSTOM ,true, "창식",R.drawable.default_profile,"나와 16m","12분전","공대","인싸","한식","족발","해병대"))
//            add(MapSignal(SignalMode.DEFAULT ,false, "고악",R.drawable.default_profile,"나와 17m","17분전","컴공","양식","디저트","치킨","콜라"))
//            add(MapSignal(SignalMode.CUSTOM ,true, "웅",R.drawable.default_profile,"나와 25m","22분전","뉴페","긔욤","경양식","보쌈","서버"))
//        }

        getList()

        val mapSignalListRVAdapter = MapSignalListRVAdapter(mapSignalListDatas)
        binding.mapSignalListRecyclerviewGrid.adapter = mapSignalListRVAdapter
        binding.mapSignalListRecyclerviewGrid.layoutManager = GridLayoutManager(this,2)

        //recycler view



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

    fun getList(){
        var retrofit = getRetorfit()
        var MapService = retrofit.create(MapService::class.java)

        var userIdx: Int = 1

        MapService.getSignalInfo(userIdx)
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
                            Log.d("MapListActivity", signalUserIdxList.toString())

                        }
                    }
                }

                override fun onFailure(call: Call<SignalInfoAuthResponse>, t: Throwable) {
                    Log.d("MapListActivity","실패")
                }


            })

    }

}