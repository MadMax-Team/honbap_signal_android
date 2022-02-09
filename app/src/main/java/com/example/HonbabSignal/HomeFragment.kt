package com.example.HonbabSignal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.databinding.FragmentHomeBinding
import com.example.HonbabSignal.databinding.FragmentLookBinding


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        //home에서 login버튼 누르면 login activity로 넘어갑니다
        binding.homeLoginTv.setOnClickListener {
            //Toast.makeText(activity, "Its toast!", Toast.LENGTH_SHORT).show();

            val intent = Intent(activity, LogInActivity::class.java)
            startActivity(intent)
        }

        //home에서 회원가입 버튼 누르면 signup activity로 넘어갑니다
        binding.homeSignUpTv.setOnClickListener {
            //Toast.makeText(activity, "Its toast!", Toast.LENGTH_SHORT).show();

            val intent = Intent(activity, SignUpActivity::class.java)
            startActivity(intent)
        }

        //(test용) home에서 프로필 버튼을 누르면 profile activity로 넘어갑니다
        binding.homeProfileTv.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }


}