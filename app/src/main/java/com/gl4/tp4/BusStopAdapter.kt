package com.gl4.tp4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.entities.Schedule

class BusStopAdapter(private var schedules: List<Schedule>) :
    RecyclerView.Adapter<BusStopAdapter.BusStopViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bus_stop, parent, false)
        return BusStopViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        val schedule = schedules[position]
        holder.bind(schedule)
    }

    override fun getItemCount(): Int {
        return schedules.size
    }

    inner class BusStopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stopNameTextView: TextView = itemView.findViewById(R.id.stopNameTextView)
        private val arrivalTimeTextView: TextView = itemView.findViewById(R.id.arrivalTimeTextView)

        fun bind(schedule: Schedule) {
            stopNameTextView.text = schedule.stopName
            arrivalTimeTextView.text = schedule.arrivalTime.toString()
        }
    }

    fun updateData(newSchedules: List<Schedule>) {
        schedules = newSchedules
        notifyDataSetChanged()
    }
}
