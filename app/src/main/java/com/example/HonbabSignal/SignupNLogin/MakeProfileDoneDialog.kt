package com.example.HonbabSignal.SignupNLogin

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.HonbabSignal.databinding.DialogMakeProfileBinding
import com.example.HonbabSignal.databinding.DialogMakeProfileDoneBinding

class MakeProfileDoneDialog : DialogFragment() {
    private var _binding : DialogMakeProfileDoneBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogMakeProfileDoneBinding.inflate(inflater, container, false)
        val view = binding?.root
        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        binding?.makeProfileDoneCompleteTv?.setOnClickListener {
            dismiss()
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    }