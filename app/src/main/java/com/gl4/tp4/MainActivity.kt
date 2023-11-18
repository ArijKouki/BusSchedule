package com.gl4.tp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.AppDatabase
import com.gl4.tp4.database.entities.Schedule

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

        val schedules: List<Schedule> = appDatabase.scheduleDao().getAll()

        busStopAdapter.updateData(schedules)
    }
}