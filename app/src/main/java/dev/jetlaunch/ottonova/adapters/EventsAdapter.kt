package dev.jetlaunch.ottonova.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.jetlaunch.ottonova.R
import dev.jetlaunch.ottonova.models.ITimeLineRecyclerItem
import dev.jetlaunch.ottonova.models.TimelineModel

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventsVH>() {
    var items = ArrayList<ITimeLineRecyclerItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    private var onItemClick: ((TimelineModel) -> Unit)? = null


    fun setOnclickListener(clickListener: (TimelineModel) -> Unit) {
        onItemClick = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsVH {
        val view =  LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return EventsVH(view)
    }

    override fun getItemCount(): Int {
    return items.size
    }

    override fun onBindViewHolder(holder: EventsVH, position: Int) {
        val item = items[position]
        holder.text.text = item.getText()
        if(item is TimelineModel){
            holder.subtext?.text = item.description
            holder.itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is TimelineModel -> R.layout.event_layout
            else -> R.layout.date_layout
        }
    }

    class EventsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.tv_title)
        val subtext: TextView? = itemView.findViewById(R.id.tv_subtitle)
    }
}