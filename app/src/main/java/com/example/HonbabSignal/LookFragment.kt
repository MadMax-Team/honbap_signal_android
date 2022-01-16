package com.example.HonbabSignal

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.databinding.FragmentLookBinding
import android.content.Intent
import android.widget.Toast
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource


class LookFragment : Fragment() {

    lateinit var binding: FragmentLookBinding
    lateinit var locationSource: FusedLocationSource
    lateinit var naverMap: NaverMap




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLookBinding.inflate(inflater, container, false)

//        //FusedLocationSource는 런타임 권한 처리를 위해 생성자에 액티비티 객체를 전달하고 권한 요청 코드를 지정해야함함
//        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        //lookBtn 누르면 MapActivity로 넘어가기
        binding.lookBtn.setOnClickListener {
            //Toast.makeText(activity, "Its toast!", Toast.LENGTH_SHORT).show();

            val intent = Intent(activity, MapActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

//    override fun onRequestPermissionsResult(requestCode: Int,
//                                            permissions: Array<String>,
//                                            grantResults: IntArray) {
//        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
//                grantResults)) {
//            if (!locationSource.isActivated) { // 권한 거부됨
//                naverMap.locationTrackingMode = LocationTrackingMode.None
//            }
//            return
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }
//
//    override fun onMapReady(naverMap: NaverMap) {
//        this.naverMap = naverMap
//        naverMap.locationSource = locationSource
//        naverMap.locationTrackingMode = LocationTrackingMode.Follow
//
//        naverMap.addOnLocationChangeListener { location ->
//            Toast.makeText(activity,"${location.latitude}, ${location.longitude}",Toast.LENGTH_LONG).show()
//        }
//
//    }
//
//    companion object {
//        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
//    }

}