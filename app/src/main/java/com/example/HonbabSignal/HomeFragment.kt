package com.example.HonbabSignal

import CustomDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HonbabSignal.AuthResponses.SignalOnResponse
import com.example.HonbabSignal.DM.Signal
import com.example.HonbabSignal.RetrofitSevices.SignalService
import com.example.HonbabSignal.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    var signalToMeList = ArrayList<Signal>()
    private val dmToMeList = ArrayList<Signal>()
    private val signalFromMeList = ArrayList<Signal>()
    var signalIdxList = ArrayList<SignalIdxList>()
    var retrofit = getRetorfit()
    var signalService = retrofit.create(SignalService::class.java)
    lateinit var jwt: String

    fun deleteSignal(signalIdx: SignalIdxList){
        Log.d("deleteSignal",signalIdx.toString())
        signalService.deleteSignal(jwt, signalIdx.signalIdx)
            .enqueue(object:Callback<SignalOnResponse>{
                override fun onResponse(call: Call<SignalOnResponse>, response: Response<SignalOnResponse>) {
                    Log.d("delete","success")
                }

                override fun onFailure(call: Call<SignalOnResponse>, t: Throwable) {
                    Log.d("delete","error")
                }

            })

    }

    private fun retrofitDeleteSignal(jwt: String){

        Log.d("getSignalIdx", "함수 실행")
        Log.d("getSignalIdx-jwt",jwt)

        signalService.getSignalIdx(jwt)
            .enqueue(object: Callback<ProfileSignalIdxResponse>{
                override fun onResponse(
                    call: Call<ProfileSignalIdxResponse>,
                    response: Response<ProfileSignalIdxResponse>
                ) {
                    var resp = response.body()!!

                    Log.d("getSignalIdx",resp.toString())
                    for (i in resp.result){
                        Log.d("getSignalIdx-i",i.signalIdx.toString())
                        deleteSignal(i)

                    }

                }

                override fun onFailure(call: Call<ProfileSignalIdxResponse>, t: Throwable) {
                    Log.d("getSignalIdx","error")
                }

            })

        Log.d("getSinalIdx","함수 끝")

    }

    fun setAdapter(jwt: String){
        var signalToMeListAdapter = HomeSignalListAdapter(signalToMeList, jwt)
        var dmToMeListAdapter = HomeSignalListAdapter(dmToMeList, jwt)
        var signalFromMeListAdapter = HomeSignalListAdapter(signalFromMeList, jwt)

        binding.homeSignalToMeList.adapter = signalToMeListAdapter
        binding.homeDmToMeList.adapter = dmToMeListAdapter
        binding.homeSignalFromMeList.adapter = signalFromMeListAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        dmToMeList.apply {
            add(Signal("디엠1",12))
            add(Signal("dm2",12))
        }

        signalFromMeList.apply {
            add(Signal("내가 보낸",12))
            add(Signal("dm2s",12))
        }

        val spf_jwt = this.getActivity()?.getSharedPreferences("jwt", Context.MODE_PRIVATE)
        jwt = spf_jwt?.getString("jwt","").toString()
        Log.d("jwt",jwt)

        setAdapter(jwt)






        fun retrofitPostSignal(){

            var sigPromiseTime: String = ""
            var sigPromiseArea: String = ""

            signalService.addOnSignal(jwt, sigPromiseTime, sigPromiseArea)
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

        fun setSignalToMe(){

            signalService.getSignalToMe(jwt)
                .enqueue(object: Callback<ProfileSignalNicknameResponse>{
                    override fun onResponse(
                        call: Call<ProfileSignalNicknameResponse>,
                        response: Response<ProfileSignalNicknameResponse>
                    ) {
                        var resp = response.body()!!
                        for (i in resp.result){
                            signalToMeList.apply{
                                add(Signal(i.nickName,0))
                            }
                        }
                        setAdapter(jwt)


                    }

                    override fun onFailure(
                        call: Call<ProfileSignalNicknameResponse>,
                        t: Throwable
                    ) {
                        TODO("Not yet implemented")
                    }

                })
        }

        fun setSignalFromMe(){

        }


        setSignalToMe()

        binding.homeAfterSignalOffIv.setOnClickListener {
            binding.homeAfterSignalOffIv.visibility = View.GONE
            binding.homeAfterSignalOnIv.visibility = View.VISIBLE

            binding.homeSignalToMeList.visibility = View.VISIBLE
            binding.homeSignalToMeNoneList.visibility = View.GONE

            binding.homeDmToMeList.visibility = View.VISIBLE
            binding.homeDmToMeNoneList.visibility = View.GONE

            binding.homeSignalFromMeList.visibility = View.VISIBLE
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

            binding.homeDmToMeList.visibility = View.GONE
            binding.homeDmToMeNoneList.visibility = View.VISIBLE

            binding.homeSignalFromMeList.visibility = View.GONE
            binding.homeSignalFromMeNoneList.visibility = View.VISIBLE

            retrofitDeleteSignal(jwt)


        }



        return binding.root
    }


}