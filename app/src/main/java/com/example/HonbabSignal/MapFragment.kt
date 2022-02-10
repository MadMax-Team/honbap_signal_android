package com.example.HonbabSignal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.databinding.FragmentMapBinding
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Transformations.map
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import java.lang.reflect.Array.newInstance


class MapFragment : Fragment(), OnMapReadyCallback {

    lateinit var binding: FragmentMapBinding
    lateinit var locationSource: FusedLocationSource
    lateinit var naverMap: NaverMap


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)

//       //FusedLocationSource는 런타임 권한 처리를 위해 생성자에 액티비티 객체를 전달하고 권한 요청 코드를 지정해야함함
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)



        //lookBtn 누르면 MapActivity로 넘어가기
        binding.lookBtn.setOnClickListener {
            //Toast.makeText(activity, "Its toast!", Toast.LENGTH_SHORT).show();

            val intent = Intent(activity, MapListActivity::class.java)
            startActivity(intent)
        }
        return binding.root
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

        //Toast.makeText(this, "onMapReady 실행 중", Toast.LENGTH_SHORT).show();
        Log.d("mapfragment","onMapReady")
        this.naverMap = naverMap
        val uiSettings = naverMap.uiSettings

        //나침반:비활, 현위치버튼:활성화
        uiSettings.isCompassEnabled = false
        uiSettings.isLocationButtonEnabled = true

        naverMap.locationSource = locationSource

        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        //좌표 변경 시 토스트로 표시
        naverMap.addOnLocationChangeListener { location ->
            Log.d("mapfragment", "${location.latitude}, ${location.longitude}")
        }
    }



    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

}