package com.example.HonbabSignal.SignupNLogin

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.HonbabSignal.RetrofitSevices.SignUpService
import com.example.HonbabSignal.SignUpAuthResponse
import com.example.HonbabSignal.databinding.DialogMakeProfileBinding
import com.example.HonbabSignal.getRetorfit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MakeProfileDialog : DialogFragment() {
    private var _binding: DialogMakeProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogMakeProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var userIdx: Int
        var userIntroduce: String
        var taste: String
        var hateFood: String
        var interest: String
        var preferArea: String
        var mbti: String

        binding.makeProfileCompleteTv.setOnClickListener {
            userIntroduce = binding.makeProfileIntroduceEt.text.toString()
            taste = binding.makeProfileLikeEt.text.toString()
            hateFood = binding.makeProfileDislikeEt.text.toString()
            interest = binding.makeProfileInterestEt.text.toString()
            preferArea = binding.makeProfileAreaEt.text.toString()
            mbti = binding.makeProfileMbtiEt.text.toString()

            var retrofit = getRetorfit()
            var signUpService = retrofit.create(SignUpService::class.java)

            signUpService.profileUp(
                //userIdx,
                userIntroduce,
                taste,
                hateFood,
                interest,
                preferArea,
                mbti,

            ).enqueue(object : Callback<SignUpAuthResponse> {
                override fun onResponse(call: Call<SignUpAuthResponse>, response: Response<SignUpAuthResponse>) {
                    var respProfile = response.body()!!

                    when (respProfile.code) {
                        1000 -> {
                            Log.d("makeProfile", "success")
                            dismiss()    // 대화상자를 닫는 함수

                            val dialog = MakeProfileDoneDialog()
                            dialog.show(parentFragmentManager, "MakeProfileDoneDialog")
                        }
                        }

                    }

                override fun onFailure(call: Call<SignUpAuthResponse>, t: Throwable) {
                    Log.d("makeProflile","error")
                }
            })




        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}