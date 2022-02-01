package com.example.HonbabSignal

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityMainBinding
import com.example.HonbabSignal.databinding.ActivityMapBinding
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.widget.ZoomControlView

class MapActivity:AppCompatActivity(), OnMapReadyCallback {

    lateinit var binding: ActivityMapBinding
    lateinit var mapView: MapView

    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap

    private var mapSignalListDatas = ArrayList<MapSignal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        mapView = findViewById<MapView>(R.id.map_view)
        mapView.onCreate(savedInstanceState)


        //지도 사용권한을 받아옴
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance(NaverMapOptions().zoomControlEnabled(false))
                .also {
                    fm.beginTransaction().add(R.id.map, it).commit()
                }
        mapFragment.getMapAsync(this);

        //임시 데이터리스트 생성(서버 없어서 해봄)
        mapSignalListDatas.apply{
            add(MapSignal("고악",R.drawable.kakao_default_profile_image,"대전"))
            add(MapSignal("고악",R.drawable.kakao_default_profile_image,"대전"))
        }

        //recyclerView
        val mapSignalListAdapter = MapSignalListAdapter(mapSignalListDatas)
        //리사이클러뷰와 어댑터 연결
        binding.mapActivityRV.adapter = mapSignalListAdapter

        //리스너 객체 생성 및 전달

    }
    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
                grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                Log.d("tag", "권한 거부")
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onMapReady(naverMap: NaverMap) {

        Toast.makeText(this, "onMapReady 실행 중", Toast.LENGTH_SHORT).show();

        this.naverMap = naverMap
        val uiSettings = naverMap.uiSettings
        //나침반:비활, 현위치버튼:활성화
        uiSettings.isCompassEnabled = false
        uiSettings.isLocationButtonEnabled = true

        naverMap.locationSource = locationSource

        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        //좌표 변경 시 토스트로 표시
        naverMap.addOnLocationChangeListener { location ->
            Toast.makeText(this, "${location.latitude}, ${location.longitude}",
                Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}