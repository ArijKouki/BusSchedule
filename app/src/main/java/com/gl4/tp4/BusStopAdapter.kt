package com.gl4.tp4

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.entities.Schedule
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class BusStopAdapter(private var schedules: List<Schedule>,private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<BusStopAdapter.BusStopViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(schedule: Schedule)
    }


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

        init {
            // Set click listener for the item
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(schedules[position])
                }
            }
        }

        fun bind(schedule: Schedule) {
            stopNameTextView.text = schedule.stopName
            val date = Date(schedule.arrivalTime* 1000)
            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getTimeZone("Africa/Tunis")
            arrivalTimeTextView.text = dateFormat.format(date)
        }
    }


    fun updateData(newSchedules: List<Schedule>) {
        schedules = newSchedules
        notifyDataSetChanged()
    }
}
