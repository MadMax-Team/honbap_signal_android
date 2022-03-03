package com.example.HonbabSignal

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import retrofit2.Call
import retrofit2.Callback
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.databinding.FragmentHomeBinding
import retrofit2.Response


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
            binding.homeAfterLoginAfterMatchingInfoLl.visibility = View.VISIBLE
            binding.homeAfterLoginAfterMatchingSeeProfile.visibility = View.VISIBLE

        }

        fun retrofitPostSignal(){
            var retrofit = getRetorfit()
            var SignalService = retrofit.create(SignalService::class.java)
            var userIdx: Int = 1
            var signalIdx: String = "1"
            var applyIdx: String = "1"

            SignalService.addSignal(userIdx, signalIdx, applyIdx )
                .enqueue(object: Callback<SignalResponse>{
                    override fun onResponse(
                        call: Call<SignalResponse>,
                        response: Response<SignalResponse>
                    ) {

                        var respIdx = response.body()!!
                        when (respIdx.code){
                            1000 -> {
                                Log.d("HomeFragment",respIdx.code.toString())
                            }

                        }
                    }

                    override fun onFailure(call: Call<SignalResponse>, t: Throwable) {
                        Log.d("HomeFragment", "signal add onFailure")
                    }
                })
        }


        return binding.root
    }


}