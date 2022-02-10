import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.HonbabSignal.MapSignal
import com.example.HonbabSignal.R
import kotlinx.android.synthetic.main.item_map_signal_list.view.*

class ListAdapterGrid(var list: ArrayList<MapSignal>): RecyclerView.Adapter<ListAdapterGrid.GridAdapter>() {

    class GridAdapter(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridAdapter {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_map_signal_list, parent, false)
        var width = (parent.measuredWidth-100)/2
        view.layoutParams.width = width
        return GridAdapter(view)
    }

    override fun onBindViewHolder(holder: GridAdapter, position: Int) {
        holder.layout.item_map_signal_nickname_tv.text = list[position].name
        holder.layout.item_map_signal_list_profile_iv.setImageResource(list[position].profileImageUrl!!)
        holder.layout.item_map_signal_location_tv.text = list[position].location
        holder.layout.item_map_signal_time_tv.text = list[position].time
        holder.layout.item_map_signal_tag1_tv.text = list[position].tag1
        holder.layout.item_map_signal_tag2_tv.text = list[position].tag2
        holder.layout.item_map_signal_tag3_tv.text = list[position].tag3
        holder.layout.item_map_signal_tag4_tv.text = list[position].tag4
        holder.layout.item_map_signal_tag5_tv.text = list[position].tag5

        holder.layout.layoutListItem.setOnClickListener {
            Toast.makeText(holder.layout.context, "${list[position]} Click!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}