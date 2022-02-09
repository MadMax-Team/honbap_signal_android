package com.example.HonbabSignal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.databinding.FragmentLockerBinding


class LockerFragment : Fragment() {

    lateinit var binding: FragmentLockerBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLockerBinding.inflate(inflater, container, false)

        binding.lockerBtn.setOnClickListener {
            //Toast.makeText(activity, "Its toast!", Toast.LENGTH_SHORT).show();
//
//            val intent = Intent(activity, EditingProfileActivity::class.java)
//            startActivity(intent)
        }

        return binding.root
    }

}