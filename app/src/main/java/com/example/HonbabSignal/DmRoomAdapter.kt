package com.example.HonbabSignal

import android.content.ClipDescription
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.HonbabSignal.databinding.ItemMyDmBinding
import com.example.HonbabSignal.databinding.ItemYourDmBinding
import java.util.ArrayList

class DmRoomAdapter(val context: Context, val arrayList: ArrayList<DmModel>)
    :  RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    //internal lateinit var preferences: SharedPreferences

    //클릭 인터페이스 정의
    interface MyitemClickListener{
        fun onItemClick(dmModel: DmModel)
    }

    //리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mItemClickListner: DmRoomAdapter.MyitemClickListener

    fun addItem(item: DmModel) {//아이템 추가
        if (arrayList != null) {
            arrayList.add(item)
            Toast.makeText(this.context, item.name, Toast.LENGTH_SHORT).show()
        }
    }

    // 뷰홀더 생성 시 호출 -> 아이템 뷰객체를 만들어서 뷰홀더에 던짐
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        //getItemViewType 에서 뷰타입 1을 리턴받았다면 내채팅레이아웃을 받은 Holder를 리턴
        //val v = viewType
        Log.d("say","I'm in onCreateViewHolder")

        if(viewType == 1){
            //view = LayoutInflater.from(context).inflate(R.layout.item_my_dm, viewGroup, false)
            val binding: ItemMyDmBinding = ItemMyDmBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return Holder1(binding)
        }
        //getItemViewType 에서 뷰타입 2을 리턴받았다면 상대채팅레이아웃을 받은 Holder2를 리턴
        else{
            //view = LayoutInflater.from(context).inflate(R.layout.item_your_dm, viewGroup, false)
            val binding: ItemYourDmBinding = ItemYourDmBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return Holder2(binding)
        }
    }

    override fun onBindViewHolder(viewHolder:  RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is Holder1) {

            viewHolder.bind(arrayList[position])
            //(viewHolder as Holder1).dm_Text?.setText(arrayList.get(position).script)
            //(viewHolder as Holder1).dm_Time?.setText(arrayList.get(position).date_time)

            //adapter 내부에서 클릭 시 반응하면 외부에선 떨어짐
            //그래서 interface 사용함
            viewHolder.itemView.setOnClickListener {mItemClickListner.onItemClick(arrayList[position])}

        }
        else if(viewHolder is Holder2) {
            viewHolder.bind(arrayList[position])
            viewHolder.itemView.setOnClickListener {mItemClickListner.onItemClick(arrayList[position])}
        }
    }

    override fun getItemCount(): Int = arrayList.size

    //내가친 채팅 뷰홀더
    inner class Holder1(val binding: ItemMyDmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dmModel:DmModel){
            binding.itemMyDmMeName.text = dmModel.script
        }
    }

    //상대가 친 채팅 뷰홀더
    inner class Holder2(val binding: ItemYourDmBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dmModel: DmModel){
            binding.itemYourDmText.text = dmModel.script
            binding.itemYourDmYouName.text = dmModel.name
        }

    }

    override fun getItemViewType(position: Int): Int {
        //여기서 뷰타입을 1, 2로 바꿔서 지정해줘야 내채팅 너채팅을 바꾸면서 쌓을 수 있음
        //이전 페이지에서 데려온 아이들 (없어서 오류나는 중)
        //preferences = context.getSharedPreferences("USERSIGN", Context.MODE_PRIVATE)

        //내 아이디와 arraylist의 name이 같다면 내꺼 아니면 상대꺼
        //return if (arrayList.get(position).name == preferences.getString("name","")) {

        val i = position
        Log.d("say",arrayList.get(i).name)
        return if (arrayList.get(position).name == "고악") {
            1
            Log.d("say","return 1")
        } else {
            2
            Log.d("say","return 2")
        }
    }


}