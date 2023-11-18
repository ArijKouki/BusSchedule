package com.gl4.tp4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.AppDatabase
import com.gl4.tp4.database.entities.Schedule
import com.gl4.tp4.viewmodels.BusScheduleViewModel
import com.gl4.tp4.viewmodels.BusScheduleViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() , BusStopAdapter.OnItemClickListener{

    private lateinit var appDatabase: AppDatabase
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private lateinit var busStopAdapter: BusStopAdapter
    private val viewModel: BusScheduleViewModel by viewModels {
        BusScheduleViewModelFactory((application as BusScheduleApplication).database.scheduleDao())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDatabase = AppDatabase.getDatabase(this)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        busStopAdapter = BusStopAdapter(emptyList(), this)
        recyclerView.adapter = busStopAdapter

        viewModel.fullSchedule().observe(this) { Log.d("it: ",it.toString()); busStopAdapter.updateData(it) }

    }

    override fun onItemClick(schedule: Schedule) {
        // Handle item click, launch DetailsActivity with selected stop name
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("STOP_NAME", schedule.stopName)
        startActivity(intent)
    }


}