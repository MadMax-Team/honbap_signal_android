package com.example.HonbabSignal

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.example.HonbabSignal.RetrofitSevices.ReviewService
import com.example.HonbabSignal.databinding.ActivityPostScriptBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostScriptActivity: Activity() {
    lateinit var binding: ActivityPostScriptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostScriptBinding.inflate(layoutInflater)

        var dm = getApplicationContext().getResources().getDisplayMetrics()

        var width = (dm.widthPixels * 0.9) // Display 사이즈의 90%
        var height = (dm.heightPixels * 0.8) // Display 사이즈의 90%

        getWindow().getAttributes().width = width.toInt()
        getWindow().getAttributes().height = height.toInt()


        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        //retrofit post 함수
        fun retrofitPostReview(){
            var retrofit = getRetorfit()
            var ReviewService = retrofit.create(ReviewService::class.java)
            var signalIdx: Int = 1
            var userIdx: Int = 1
            var writerIdx: Int = 1
            var comment: String = "임시 comment"
            var star: Int = 5

            ReviewService.addSignal(signalIdx, userIdx, writerIdx, comment, star )
                .enqueue(object: Callback<ReviewResponse> {
                    override fun onResponse(
                        call: Call<ReviewResponse>,
                        response: Response<ReviewResponse>
                    ) {

                        var respIdx = response.body()!!
                        when (respIdx.code){
                            1000 -> {
                                Log.d("PostScriptActivity",respIdx.code.toString())
                            }
                            2014 -> {
                                Log.d("PostScriptActivity",respIdx.code.toString())
                            }
                            2015 -> {
                                Log.d("PostScriptActivity",respIdx.code.toString())
                            }
                            4000 -> {
                                Log.d("PostScriptActivity",respIdx.code.toString())
                            }

                        }
                    }

                    override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                        Log.d("PostScriptActivity","DB 오류")
                    }
                })
        }


        binding.postScriptGoodMannersCv.setOnClickListener{

        }
        binding.postScriptNiceMenuPickCv.setOnClickListener{

        }
        binding.postScriptGoodCommunicationMannersCv.setOnClickListener{

        }
        binding.postScriptNiceTimeCv.setOnClickListener{

        }

        binding.postScriptSaveBtn.setOnClickListener{
            retrofitPostReview()
        }



    }
}