package com.example.HonbabSignal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.HonbabSignal.Map.MapSignal
import com.example.HonbabSignal.Map.SignalMode
import com.example.HonbabSignal.databinding.ItemMapSignalListCustomBinding
import com.example.HonbabSignal.databinding.ItemMapSignalListDefaultBinding

class MapSignalListRVAdapter(private var signalList : ArrayList<MapSignal>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //클릭인터페이스 정의
    public interface MyItemClickListner{
        fun onItemClick(signal : MapSignal)
    }

    //리스너 객체를 전달받을 함수랑 리스너 객체를 전달받을 변수
    private lateinit var mItemClickListner : MyItemClickListner

    public fun setMyItemClickListener(itemClickListner : MyItemClickListner){
        mItemClickListner = itemClickListner
    }


    //default(추가정보 입력안했을때 siganal) 모드 ViewHolder
    inner class DefaultViewHolder(private val binding : ItemMapSignalListDefaultBinding) :
    RecyclerView.ViewHolder(binding.root){

        fun bind(signal : MapSignal){
            //지금 프로필 이미지 url로 들어가있지 않음 -> 향후 변경해야함
            //binding.itemMapSignalListDefaultProfileIv.setImageResource(signal.profileImageUrl!!)
            binding.itemMapSignalListDefaultNicknameTv.text = signal.nickName
            //binding.itemMapSignalListDefaultLocationTv.text = signal.location
            //binding.itemMapSignalListDefaultTimeTv.text = signal.time


        }
    }
    //custom(추가 정보 입력했을때 signal) 모드 viewHolder
    inner class CustomViewHolder(private val binding : ItemMapSignalListCustomBinding) :
    RecyclerView.ViewHolder(binding.root){

        fun bind(signal : MapSignal){
            //binding.itemMapSignalListCustomProfileIv.setImageResource(signal.profileImageUrl!!)
            binding.itemMapSignalListCustomNicknameTv.text = signal.nickName
            //binding.itemMapSignalListCustomLocationTv.text = signal.location
            //binding.itemMapSignalListCustomTimeTv.text = signal.time

        }

    }
    //viewholder를 생성해야 할때 호출
    //아이템 뷰 객체를 만들어서 viewHolder에 전달
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            SignalMode.DEFAULT -> {
                 DefaultViewHolder(
                    ItemMapSignalListDefaultBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else ->{
                CustomViewHolder(
                    ItemMapSignalListCustomBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
    //viewHolder에 데이터 바인딩 할때마다 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(signalList[position].checkSigWrite){
            SignalMode.DEFAULT->{
                (holder as DefaultViewHolder).bind(signalList[position])
                holder.itemView.setOnClickListener { mItemClickListner.onItemClick(signalList[position]) }
            }
            else ->{
                (holder as CustomViewHolder).bind(signalList[position])
                holder.itemView.setOnClickListener { mItemClickListner.onItemClick(signalList[position]) }
            }

        }
    }

    override fun getItemCount(): Int = signalList.size

    //ViewType 설정
    override fun getItemViewType(position: Int): Int {
        return signalList[position].checkSigWrite
    }
}