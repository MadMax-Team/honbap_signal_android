package com.example.HonbabSignal

import ListAdapterGrid
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.HonbabSignal.databinding.ActivityMapListBinding
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.android.synthetic.main.activity_map_list.*

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
//        mapView = findViewById<MapView>(R.id.map_view)
//        mapView.onCreate(savedInstanceState)


//        //지도 사용권한을 받아옴
//        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
//
//        val fm = supportFragmentManager
//        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
//            ?: MapFragment.newInstance(NaverMapOptions().zoomControlEnabled(false))
//                .also {
//                    fm.beginTransaction().add(R.id.map, it).commit()
//                }
//        mapFragment.getMapAsync(this);

        //임시 데이터리스트 생성(서버 없어서 해봄)
        mapSignalListDatas.apply{
            add(MapSignal(true,"곤",R.drawable.geon_profile,"나와 555m","4분전","일식","돈까스","젤리","20대","초콜릿"))
            add(MapSignal(false,"코뿡",R.drawable.cobboong,"나와 211m","1분전","헤비토커","초밥","고기","샤브샤브","사이다"))
            add(MapSignal(true, "데옹",R.drawable.deong,"나와 105m","6분전","푸드파이터","떡볶이","국밥","고기","풀"))
            add(MapSignal(false, "도동",R.drawable.dodong_profile,"나와 15m","10분전","디자인","맥주","피자","20대","고기"))
            add(MapSignal(true, "창식",R.drawable.default_profile,"나와 95m","122분전","공대","인싸","한식","족발","해병대"))
            add(MapSignal(false, "고악",R.drawable.default_profile,"나와 915m","123분전","공대","인싸","한식","족발","해병대"))
            add(MapSignal(true, "웅",R.drawable.default_profile,"나와 1195m","1200분전","공대","인싸","한식","족발","해병대"))
        }

        //recyclerView
        Log.d("say","make recyclerview")
        var listManager = GridLayoutManager(this,2)
        var listAdapter = ListAdapterGrid(mapSignalListDatas)
        //val mapSignalListAdapter = MapSignalListAdapter(mapSignalListDatas)

        var recyclerList = recyclerGridView.apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }
    }

}