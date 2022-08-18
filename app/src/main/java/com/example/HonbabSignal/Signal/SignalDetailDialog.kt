package com.example.HonbabSignal.Signal

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.HonbabSignal.ProfilePatchResponse
import com.example.HonbabSignal.RetrofitSevices.SignalService
import com.example.HonbabSignal.databinding.DialogSignalDetailBinding
import com.example.HonbabSignal.getRetorfit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomDialog2 : DialogFragment() {
    private var _binding: DialogSignalDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogSignalDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        var retrofit = getRetorfit()
        var SignalService = retrofit.create(SignalService::class.java)

        val spf_jwt = this.getActivity()?.getSharedPreferences("jwt", Context.MODE_PRIVATE)
        val jwt: String = spf_jwt?.getString("jwt","").toString()
        Log.d("jwt",jwt)

        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.signalDetailPassTv.setOnClickListener {
            dismiss()    // 대화상자를 닫는 함수
        }
        binding.signalDetailCompleteTv.setOnClickListener {
            //retrofit
            var time = binding.signalDetailTimeEt.text.toString()
            var location = binding.signalDetailLocationEt.text.toString()
            var menu = binding.signalDetailPreferLocationMenuEt.text.toString()

            SignalService.patchSignal(jwt, time, location, menu)
                .enqueue(object: Callback<ProfilePatchResponse>{
                    override fun onResponse(
                        call: Call<ProfilePatchResponse>,
                        response: Response<ProfilePatchResponse>
                    ) {
                        var resp = response.body()!!
                        when(resp.code){
                            1000->{
                                Log.d("signalDetail","시그널 생성이 완료되었습니다.")
                            }
                            else->{
                                Log.d("signalDetail","시그널 생성 문제있음")
                            }
                        }
                    }

                    override fun onFailure(call: Call<ProfilePatchResponse>, t: Throwable) {
                        Log.d("signalDetail","시그널 생성 문제있음")
                    }

                })

            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}