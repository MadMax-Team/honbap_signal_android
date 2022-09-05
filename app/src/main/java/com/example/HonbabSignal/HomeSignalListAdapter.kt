package com.example.HonbabSignal

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.HonbabSignal.DM.Signal
import com.example.HonbabSignal.Map.PopupActivity
import com.example.HonbabSignal.RetrofitSevices.SignalService
import com.example.HonbabSignal.databinding.ItemHomeProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeSignalListAdapter(private val signalList: ArrayList<Signal>) :
    RecyclerView.Adapter<HomeSignalListAdapter.ViewHolder>()
{

    //클릭 인터페이스 정의
    interface MyitemClickListener{

    }

    //리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mItemClickListener: MyitemClickListener


    //뷰홀더 생성 시 호출 -> 아이템 뷰객체를 만들어서 뷰홀더에 던짐짐
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHomeProfileBinding = ItemHomeProfileBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    // 뷰홀더에 Data 를 binding 위아래로 스크롤 할 때마다 엄청나게 호출
    // 뷰홀더가 매개변수로 들어와서 자식뷰에 접근가능 => 데이터 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(signalList[position])
        holder.onClick(signalList[position])
        //adapter 내부에서 클릭 시 반응하면 외부에선 떨어짐
        //그래서 interface 사용함함

    }

    //리사이클러뷰 마지막을 알 수 있음
    override fun getItemCount() : Int = signalList.size



    //inner class로 뷰홀더
    //item 뷰 객체들을 잡고있는 그릇(?)
    inner class ViewHolder(val binding: ItemHomeProfileBinding): RecyclerView.ViewHolder(binding.root) {

        val retrofit = getRetorfit()
        var signalService = retrofit.create(SignalService::class.java)
        var userIdx: Int = 0


        fun onClick(signal: Signal){
            //프로필버튼 클릭시
            binding.itemHomeProfileProfileBtn.setOnClickListener {

                val intent = Intent(binding.itemHomeProfileProfileBtn.context, PopupActivity::class.java)
                Log.d("homeSignalListAdapter","profile click")
                val nickname:String = signal.nickname.toString()
                signalService.getSignalInfoFromNickname(nickname)
                    .enqueue(object: Callback<SignalInfoFromNicknameResponse>{
                        override fun onResponse(
                            call: Call<SignalInfoFromNicknameResponse>,
                            response: Response<SignalInfoFromNicknameResponse>
                        ) {
                            var resp = response.body()!!
                            Log.d("getInfoFromNickName",resp.result.toString())


                        }

                        override fun onFailure(
                            call: Call<SignalInfoFromNicknameResponse>,
                            t: Throwable
                        ) {
                            Log.d(
                                "getInfoFromNickName",
                                t.message.toString()
                            )
                        }

                    })


                startActivity(binding.itemHomeProfileProfileBtn.context,intent,null)

            }

            // 수락버튼 클릭시
            binding.itemHomeProfileMatchBtn.setOnClickListener {
                val nickname:String = signal.nickname.toString()
                signalService.getSignalInfoFromNickname(nickname)
                    .enqueue(object: Callback<SignalInfoFromNicknameResponse>{
                        override fun onResponse(
                            call: Call<SignalInfoFromNicknameResponse>,
                            response: Response<SignalInfoFromNicknameResponse>
                        ) {
                            var resp = response.body()!!
                            userIdx = resp.result.userIdx
                            Log.d("matchBtnClick",userIdx.toString())
                            //userIdx 받아왔고 이후 dm 연결 하면 됩니다!

                        }

                        override fun onFailure(
                            call: Call<SignalInfoFromNicknameResponse>,
                            t: Throwable
                        ) {
                            Log.d(
                                "getInfoFromNickName",
                                t.message.toString()
                            )
                        }

                    })

            }

        }
        fun bind(signal: Signal) {
            binding.itemHomeProfileNicknameTv.text = signal.nickname
            //binding.itemHomeProfileIv
        }
    }
}