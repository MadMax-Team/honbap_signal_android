package com.example.HonbabSignal.Signal

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.HonbabSignal.databinding.ActivitySignalDtatilBinding

class SignalDetailActivity(
    context : Context,
//    private val okCallBack: (String) -> Unit,
) : Dialog(context){ //뷰를 띄워야 하므로 dialog class는 context를 인자로 받는다.

    private lateinit var binding: ActivitySignalDtatilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 만들어놓은 activity_signal_detail 뷰를 띄운다.
        binding = ActivitySignalDtatilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        // 뒤로가기 버튼, 빈 화면 터치를 통해 dialog가 사라지지 않도록
        setCancelable(false)

        // background를 투명하게 만듦
        // corner radius의 적용이 보이지 않는다.
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //나가기(x)에 대한 setOnclickListner
        binding.signalDetailExitIv.setOnClickListener {  }

        //건너뛰기
        binding.signalDetailPassTv.setOnClickListener {  }

        //작성완료
        binding.signalDetailCompleteTv.setOnClickListener {  }
    }

//    //사용하고 싶은 위치에서 이 함수로 뷰 보여주면 됨
//    private fun showSignalDetail(){
//        SignalDetailActivity(this){
//        }.show()
//    }
}