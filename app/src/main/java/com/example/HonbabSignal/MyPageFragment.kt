package com.example.HonbabSignal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.databinding.FragmentMyPageBinding
import com.google.firebase.auth.UserInfo


class MyPageFragment : Fragment() {

    lateinit var binding: FragmentMyPageBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)

        binding.lockerBtn.setOnClickListener {
            val intent = Intent(activity, EditingProfileActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }

        binding.myPageSettingBtn.setOnClickListener{
            val intent = Intent(activity,UserInfoModifyActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}