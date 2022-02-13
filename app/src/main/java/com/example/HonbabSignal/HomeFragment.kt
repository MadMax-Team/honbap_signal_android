package com.example.HonbabSignal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.databinding.FragmentHomeBinding



class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeAfterSignalOffIv.setOnClickListener {
            binding.homeAfterSignalOffIv.visibility = View.GONE
            binding.homeAfterSignalOnIv.visibility = View.VISIBLE
            binding.homeAfterLoginSignalToMeLl.visibility = View.VISIBLE
            binding.homeAfterLoginDmToMeLl.visibility = View.VISIBLE
        }
        binding.homeAfterSignalOnIv.setOnClickListener {
            binding.homeAfterSignalOnIv.visibility = View.GONE
            binding.homeAfterSignalOffIv.visibility = View.VISIBLE
            binding.homeAfterLoginDmToMeLl.visibility = View.GONE
            binding.homeAfterLoginSignalToMeLl.visibility = View.GONE
        }

        binding.homeAfterLoginSignalToMeAcceptBtn.setOnClickListener{
            binding.homeAfterLoginCurrentMyMatchingStatusTv.text = "\""+binding.homeAfterLoginSignalToMeProfileNameTv.text.toString()+"\""+"님과 매칭되었습니다!★"
            binding.homeAfterLoginSignalToMeLl.visibility = View.GONE

        }




        return binding.root
    }


}