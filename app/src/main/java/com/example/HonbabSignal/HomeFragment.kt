package com.example.HonbabSignal

import CustomDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.AuthResponses.SignalOnResponse
import com.example.HonbabSignal.DM.Signal
import com.example.HonbabSignal.Map.PopupActivity
import com.example.HonbabSignal.RetrofitSevices.SignalService
import com.example.HonbabSignal.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    var signalToMeList = ArrayList<Signal>()
    val dmToMeList = ArrayList<Signal>()
    val signalFromMeList = ArrayList<Signal>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        signalToMeList.apply{
            add(Signal("고악",3))
        }

        dmToMeList.apply {
            add(Signal("디엠1",12))
            add(Signal("dm2",12))
        }


        val signalToMeListAdapter = HomeSignalListAdapter(signalToMeList)
        val dmToMeListAdapter = HomeSignalListAdapter(dmToMeList)
        val signalFromMeListAdapter = HomeSignalListAdapter(signalFromMeList)

        binding.homeSignalToMeList.adapter = signalToMeListAdapter
        binding.homeDmToMeList.adapter = dmToMeListAdapter
        binding.homeSignalFromMeList.adapter = signalFromMeListAdapter

        var retrofit = getRetorfit()
        var SignalService = retrofit.create(SignalService::class.java)


        val spf_jwt = this.getActivity()?.getSharedPreferences("jwt", Context.MODE_PRIVATE)
        val jwt: String = spf_jwt?.getString("jwt","").toString()
        Log.d("jwt",jwt)


        fun retrofitDeleteSignal(){

            SignalService.deleteSignal(jwt)
                .enqueue(object:Callback<Void>{
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Log.d("delete","success")
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("delete","error")
                    }

                })

        }

        fun retrofitPostSignal(){

            var sigPromiseTime: String = ""
            var sigPromiseArea: String = ""

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




        binding.homeAfterSignalOffIv.setOnClickListener {
            binding.homeAfterSignalOffIv.visibility = View.GONE
            binding.homeAfterSignalOnIv.visibility = View.VISIBLE
            binding.homeSignalToMeList.visibility = View.VISIBLE
            //binding.homeAfterLoginDmToMeLl.visibility = View.VISIBLE
            binding.homeSignalToMeNoneList.visibility = View.GONE
            retrofitPostSignal()

            val dialog = CustomDialog()
            dialog.show(parentFragmentManager, "CustomDialog")

        }
        binding.homeAfterSignalOnIv.setOnClickListener {
            binding.homeAfterSignalOnIv.visibility = View.GONE
            binding.homeAfterSignalOffIv.visibility = View.VISIBLE
            binding.homeSignalToMeList.visibility = View.GONE
            binding.homeSignalToMeNoneList.visibility = View.VISIBLE
            //binding.homeAfterLoginSignalToMeLl.visibility = View.GONE
            retrofitDeleteSignal()


        }



        return binding.root
    }


}