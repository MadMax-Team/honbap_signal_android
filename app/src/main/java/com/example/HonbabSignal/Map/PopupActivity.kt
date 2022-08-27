package com.example.HonbabSignal.Map

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.example.HonbabSignal.AuthResponses.SignalOnResponse
import com.example.HonbabSignal.RetrofitSevices.SignalService
import com.example.HonbabSignal.databinding.ActivityPopupBinding
import com.example.HonbabSignal.getRetorfit
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class PopupActivity : Activity(){
    lateinit var binding: ActivityPopupBinding
    lateinit var txtText:TextView

    var retrofit = getRetorfit()
    var signalService = retrofit.create(SignalService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopupBinding.inflate(layoutInflater)

        //인텐트로 넘어온 signal json가져오기
        var dm = getApplicationContext().getResources().getDisplayMetrics();

        var width = (dm.widthPixels * 0.7); // Display 사이즈의 90%
        var height = (dm.heightPixels * 0.7); // Display 사이즈의 90%


        //넘어온 정보 세팅
        //정보 예시: {"avgSpeed":"20","checkSigWrite":0,"hateFood":"mushroo,","interest":"","mbti":"isfj","nickName":"woongtest","preferArea":"anyang","sigPromiseArea":"테스트","sigPromiseTime":"2022-07-06T14:59:59.000Z",
        // "signalIdx":9,"taste":"burger","userIdx":3,"userIntroduce":"반가워요~!"}
        val jwt: String? = intent.getStringExtra("jwt")
        val nickName = intent.getStringExtra("nickName")
        val avgSpeed = intent.getStringExtra("avgSpeed")
        val hateFood = intent.getStringExtra("hateFood")
        val signalIdx = intent.getIntExtra("signalIdx",-1)
        val userIdx = intent.getIntExtra("userIdx",-1)



        binding.popupNicknameTv.text = nickName
        binding.popupNoteTv.text = signalIdx.toString()


        getWindow().getAttributes().width = width.toInt();
        getWindow().getAttributes().height = height.toInt();


        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.root);

        binding.popupBackBtn.setOnClickListener {
            val intent = Intent(this, MapListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }


        binding.popupSignalBtn.setOnClickListener{
            Toast.makeText(this@PopupActivity, "시그널이 보내졌습니다.", Toast.LENGTH_SHORT).show()
            retrofitSendSignal(jwt,signalIdx,userIdx)

        }

        binding.popupDmBtn.setOnClickListener{
            Toast.makeText(this@PopupActivity, "DM이 보내졌습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    fun retrofitSendSignal(jwt: String?,signalIdx: Int,userIdx : Int){
        signalService.addSignal(jwt,signalIdx,userIdx)
            .enqueue(object : Callback<SignalOnResponse>{
                override fun onResponse(
                    call: Call<SignalOnResponse>,
                    response: Response<SignalOnResponse>
                ) {
                    var respIdx = response.body()!!
                    when (respIdx.code) {
                        1000 -> {
                            Log.d("popupactivity", respIdx.code.toString())
                        }
                        else -> {
                            Log.d("popupActivity", "sendSignalError")

                        }
                    }
                }

                override fun onFailure(call: Call<SignalOnResponse>, t: Throwable) {
                    Log.d("popupActivity", "sendSignalError")
                }

            })
    }

}

