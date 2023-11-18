package com.gl4.tp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.AppDatabase
import com.gl4.tp4.database.entities.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var appDatabase: AppDatabase
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private lateinit var busStopAdapter: BusStopAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDatabase = AppDatabase.getDatabase(this)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        busStopAdapter = BusStopAdapter(emptyList())
        recyclerView.adapter = busStopAdapter


        loadDataFromDatabase()
    }
    private fun loadDataFromDatabase() {

        lifecycleScope.launch(Dispatchers.IO) {
            val schedules: List<Schedule> = appDatabase.scheduleDao().getAll()

            // Switch back to the main thread to update UI
            withContext(Dispatchers.Main) {
                busStopAdapter.updateData(schedules)
            }
        }
    }
}