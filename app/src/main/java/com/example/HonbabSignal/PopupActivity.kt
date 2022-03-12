package com.example.HonbabSignal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.example.HonbabSignal.RetrofitSevices.SignalService
import com.example.HonbabSignal.databinding.ActivityPopupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopupActivity : Activity(){
    lateinit var binding: ActivityPopupBinding
    lateinit var txtText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopupBinding.inflate(layoutInflater)

        var dm = getApplicationContext().getResources().getDisplayMetrics();

        var width = (dm.widthPixels * 0.7); // Display 사이즈의 90%
        var height = (dm.heightPixels * 0.7); // Display 사이즈의 90%

        getWindow().getAttributes().width = width.toInt();
        getWindow().getAttributes().height = height.toInt();


        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.root);

        binding.popupBackBtn.setOnClickListener {
            val intent = Intent(this,MapListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        fun retrofitPostSignal(){
            var retrofit = getRetorfit()
            var SignalService = retrofit.create(SignalService::class.java)
            var userIdx: Int = 1
            var signalIdx: String = "1"
            var applyIdx: String = "1"

            SignalService.addSignal(userIdx, signalIdx, applyIdx )
                .enqueue(object: Callback<SignalResponse> {
                    override fun onResponse(
                        call: Call<SignalResponse>,
                        response: Response<SignalResponse>
                    ) {

                        var respIdx = response.body()!!
                        when (respIdx.code){
                            1000 -> {
                                Log.d("PopupActivity",respIdx.code.toString())
                            }

                        }
                    }

                    override fun onFailure(call: Call<SignalResponse>, t: Throwable) {
                        Log.d("PopupActivity", "signal add onFailure")
                    }
                })
        }

        binding.popupSignalBtn.setOnClickListener{
            Toast.makeText(this@PopupActivity, "시그널이 보내졌습니다.", Toast.LENGTH_SHORT).show()
            retrofitPostSignal()

        }

        binding.popupDmBtn.setOnClickListener{
            Toast.makeText(this@PopupActivity, "DM이 보내졌습니다.", Toast.LENGTH_SHORT).show()
        }
    }

}