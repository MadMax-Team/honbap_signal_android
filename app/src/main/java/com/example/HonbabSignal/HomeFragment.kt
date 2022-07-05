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
import java.time.LocalDateTime


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {

        fun retrofitPostSignal(){
            var retrofit = getRetorfit()
            var SignalOnService = retrofit.create(SignalOnService::class.java)
            var userIdx: Int = 33
            var matchIdx: Int = 4
            var sigPromiseTime: String = "2022-01-02 11:11:11"
            var sigPromiseArea: String = "성수"

            SignalOnService.addOnSignal(userIdx, matchIdx, sigPromiseTime, sigPromiseArea)
                .enqueue(object: Callback<SignalOnResponse> {
                    override fun onResponse(
                        call: Call<SignalOnResponse>,
                        response: Response<SignalOnResponse>
                    ) {

                        var respIdx = response.body()!!
                        when (respIdx.code){
                            1000 -> {
                                Log.d("HomeFragment", respIdx.code.toString())
                            }
                            2016 -> {
                                Log.d("HomeFragment", respIdx.code.toString())
                            }
                            2017 -> {
                                Log.d("HomeFragment", respIdx.code.toString())
                            }
                            4000 -> {
                                Log.d("HomeFragment", respIdx.code.toString())
                            }
                        }
                    }

                    override fun onFailure(call: Call<SignalOnResponse>, t: Throwable) {
                        Log.d("HomeFragment", "signal add onFailure")
                    }
                })
        }

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeAfterSignalOffIv.setOnClickListener {
            binding.homeAfterSignalOffIv.visibility = View.GONE
            binding.homeAfterSignalOnIv.visibility = View.VISIBLE
            binding.homeAfterLoginSignalToMeLl.visibility = View.VISIBLE
            binding.homeAfterLoginDmToMeLl.visibility = View.VISIBLE
            retrofitPostSignal()


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




        return binding.root
    }


}