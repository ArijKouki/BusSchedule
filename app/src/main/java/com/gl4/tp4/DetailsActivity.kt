package com.gl4.tp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.entities.Schedule
import com.gl4.tp4.viewmodels.BusScheduleViewModel
import com.gl4.tp4.viewmodels.BusScheduleViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsActivity : AppCompatActivity(), BusStopAdapter.OnItemClickListener {

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private lateinit var busStopAdapter: BusStopAdapter
    private val viewModel: BusScheduleViewModel by viewModels {
        BusScheduleViewModelFactory((application as BusScheduleApplication).database.scheduleDao())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val stopName = intent.getStringExtra("STOP_NAME")

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = stopName
            setDisplayHomeAsUpEnabled(true)
        }

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        busStopAdapter = BusStopAdapter(emptyList(),this)
        recyclerView.adapter = busStopAdapter

        loadDataForStopName(stopName)

    }

    private fun loadDataForStopName(stopName: String?) {
        stopName?.let {
            lifecycleScope.launch {
                val schedules: List<Schedule>

                withContext(Dispatchers.IO) {
                    schedules = viewModel.scheduleForStopName(it)
                }

                busStopAdapter.updateData(schedules)
            }
        }
    }

    override fun onItemClick(schedule: Schedule) {
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Handle the back button press
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}