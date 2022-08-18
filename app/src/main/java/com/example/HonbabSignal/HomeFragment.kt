package com.example.HonbabSignal

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.AuthResponses.SignalOnResponse
import com.example.HonbabSignal.RetrofitSevices.SignalService
import com.example.HonbabSignal.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {


        var retrofit = getRetorfit()


        var SignalService = retrofit.create(SignalService::class.java)


        val spf_jwt = this.getActivity()?.getSharedPreferences("jwt", Context.MODE_PRIVATE)
        val jwt: String = spf_jwt?.getString("jwt","").toString()
        Log.d("jwt",jwt)


        fun retrofitDeleteSignal(){

//            SignalService.deleteSignal(jwt)
//                .enqueue(object : Callback<Void>{
//                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                        TODO("Not yet implemented")
//                    }
//
//                    override fun onFailure(call: Call<Void>, t: Throwable) {
//                        TODO("Not yet implemented")
//                    }
//
//                })

        }



        fun retrofitPostSignal(){

            var sigPromiseTime: String = "2022-01-02 11:11:11"
            var sigPromiseArea: String = "건대"

            SignalService.addOnSignal(jwt, sigPromiseTime, sigPromiseArea)
                .enqueue(object: Callback<SignalOnResponse> {
                    override fun onResponse(
                        call: Call<SignalOnResponse>,
                        response: Response<SignalOnResponse>
                    ) {

                        var respIdx = response.body()!!
                        Log.d("signalOn", respIdx.code.toString())
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
            retrofitDeleteSignal()


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