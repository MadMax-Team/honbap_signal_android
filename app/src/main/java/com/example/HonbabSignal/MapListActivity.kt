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

//
//        mapView = findViewById<MapView>(R.id.map_view)
//        mapView.onCreate(savedInstanceState)
//
//
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
            add(MapSignal("고악",R.drawable.kakao_default_profile_image,"나와 555m",4,"일식","맵찔이","초밥","동작구","초콜릿"))
            add(MapSignal("고악",R.drawable.kakao_default_profile_image,"나와 555m",4,"돈까스","맵찔이","초밥","동작구","초콜릿"))
            add(MapSignal("고악",R.drawable.kakao_default_profile_image,"나와 555m",4,"일식","맵찔이","초밥","동작구","초콜릿"))
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

//        //리사이클러뷰와 어댑터 연결
//        binding.mapActivityRecyclerView.adapter = mapSignalListAdapter
//
//        //리스너 객체 생성 및 전달
//        mapSignalListAdapter.setMytemClickListener(object : MapSignalListAdapter.MyitemClickListener{
//            override fun onItemClick(mapSignal: MapSignal) {
//                Log.d("say","I will")
//                val intent = Intent(this@MapListActivity, MapListActivity::class.java)
//
//                Log.d("say",mapSignal.name.toString())
//                //intent.putExtra("name",friend.name)
//                startActivity(intent)
//            }
//
//        })


    }

}