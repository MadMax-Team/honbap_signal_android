package com.example.HonbabSignal.Signal

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.HonbabSignal.databinding.DialogSignalCheckBinding
import com.example.HonbabSignal.databinding.DialogSignalDetailBinding


class CustomDialog2 : DialogFragment() {
    private var _binding: DialogSignalDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogSignalDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.signalDetailPassTv.setOnClickListener {
            dismiss()    // 대화상자를 닫는 함수
        }
        binding.signalDetailCompleteTv.setOnClickListener {
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}