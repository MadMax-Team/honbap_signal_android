package com.example.HonbabSignal

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.HonbabSignal.databinding.ItemMyDmBinding
import java.net.URL
import java.util.ArrayList

class DmRoomAdapter(val context: Context, val arrayList: ArrayList<DmModel>)
    :  RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    //internal lateinit var preferences: SharedPreferences

    fun addItem(item: DmModel) {//아이템 추가
        if (arrayList != null) {
            arrayList.add(item)
        }
    }

    // 뷰홀더 생성 시 호출 -> 아이템 뷰객체를 만들어서 뷰홀더에 던짐
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        //getItemViewType 에서 뷰타입 1을 리턴받았다면 내채팅레이아웃을 받은 Holder를 리턴
        if(viewType == 1){
            view = LayoutInflater.from(context).inflate(R.layout.item_my_dm, viewGroup, false)
            return Holder1(view)
        }
        //getItemViewType 에서 뷰타입 2을 리턴받았다면 상대채팅레이아웃을 받은 Holder2를 리턴
        else{
            view = LayoutInflater.from(context).inflate(R.layout.item_your_dm, viewGroup, false)
            return Holder2(view)
        }
    }

    override fun getItemCount(): Int = arrayList.size

    //내가친 채팅 뷰홀더
    inner class Holder1(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //친구목록 모델의 변수들 정의하는부분
        val dm_Text = itemView.findViewById(R.id.dm_Text)
        val dm_Time = itemView?.findViewById(R.id.dm_Time)
    }

    //상대가 친 채팅 뷰홀더
    inner class Holder2(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //친구목록 모델의 변수들 정의하는부분
        val dm_You_Image = itemView?.findViewById(R.id.dm_You_Image)
        val dm_You_Name = itemView?.findViewById(R.id.dm_You_Name)
        val dm_Text = itemView?.findViewById(R.id.dm_Text)
        val dm_Time = itemView?.findViewById(R.id.dm_Time)
    }

    override fun getItemViewType(position: Int): Int {//여기서 뷰타입을 1, 2로 바꿔서 지정해줘야 내채팅 너채팅을 바꾸면서 쌓을 수 있음
        //이전 페이지에서 데려온 아이들 (없어서 오류나는 중)
        preferences = context.getSharedPreferences("USERSIGN", Context.MODE_PRIVATE)

        //내 아이디와 arraylist의 name이 같다면 내꺼 아니면 상대꺼
        return if (arrayList.get(position).name == preferences.getString("name","")) {
            1
        } else {
            2
        }
    }

    override fun onBindViewHolder(viewHolder: DmRoomAdapter.ViewHolder, position: Int) {
        //onCreateViewHolder에서 리턴받은 뷰홀더가 Holder라면 내채팅, item_my_chat의 뷰들을 초기화 해줌
        if (viewHolder is Holder1) {
            (viewHolder as Holder1).dm_Text?.setText(arrayList.get(position).script)
            //(holder as Holder1).dm_Time?.setText(arrayList.get(position).date_time)
        }
        //onCreateViewHolder에서 리턴받은 뷰홀더가 Holder2라면 상대의 채팅, item_your_chat의 뷰들을 초기화 해줌
        else if(viewHolder is Holder2) {
            (viewHolder as Holder2).dm_You_Image?.setImageResource(R.mipmap.ic_launcher)
            (viewHolder as Holder2).dm_You_Name?.setText(arrayList.get(position).name)
            (viewHolder as Holder2).dm_Text?.setText(arrayList.get(position).script)
            //(holder as Holder2).dm_Time?.setText(arrayList.get(position).date_time)
        }
    }
}