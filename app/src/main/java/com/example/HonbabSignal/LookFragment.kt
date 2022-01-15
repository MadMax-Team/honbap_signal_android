package com.example.HonbabSignal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.databinding.FragmentLookBinding
import android.content.Intent
import android.widget.Toast


class LookFragment : Fragment() {

    lateinit var binding: FragmentLookBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLookBinding.inflate(inflater, container, false)

//        //lookBtn 누르면 MapActivity로 넘어가기
//        binding.lookBtn.setOnClickListener {
//            //Toast.makeText(activity, "Its toast!", Toast.LENGTH_SHORT).show();
//
//            val intent = Intent(activity, MapActivity::class.java)
//            startActivity(intent)
//        }
        return binding.root
    }
}