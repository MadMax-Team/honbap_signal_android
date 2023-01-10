package com.example.HonbabSignal.DM

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.HonbabSignal.databinding.ItemDmListBinding

class DmListAdapter(private val friendList: ArrayList<DmRoom>) :
    RecyclerView.Adapter<DmListAdapter.ViewHolder>()
{

    //클릭 인터페이스 정의
    interface MyitemClickListener{
        fun onItemClick(friend: DmRoom)
    }

    //리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mItemClickListner: MyitemClickListener

    fun setMytemClickListener(itemClickListener: MyitemClickListener){
        mItemClickListner = itemClickListener
    }

    //뷰홀더 생성 시 호출 -> 아이템 뷰객체를 만들어서 뷰홀더에 던짐짐
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemDmListBinding = ItemDmListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    // 뷰홀더에 Data 를 binding 위아래로 스크롤 할 때마다 엄청나게 호출
    // 뷰홀더가 매개변수로 들어와서 자식뷰에 접근가능 => 데이터 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(friendList[position])

        //adapter 내부에서 클릭 시 반응하면 외부에선 떨어짐
        //그래서 interface 사용함함이
       holder.itemView.setOnClickListener {mItemClickListner.onItemClick(friendList[position])}
    }

    //리사이클러뷰 마지막을 알 수 있음
    override fun getItemCount() : Int = friendList.size

    //inner class로 뷰홀더
    //item 뷰 객체들을 잡고있는 그릇(?)
    inner class ViewHolder(val binding: ItemDmListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: DmRoom) {
            binding.itemDmListFriendNameTv.text = friend.name
            binding.itemDmListFriendLastStringTv.text = friend.lastString
            binding.itemDmListFriendProfileImg.setImageResource(friend.profileImageUrl!!)
        }
    }
}