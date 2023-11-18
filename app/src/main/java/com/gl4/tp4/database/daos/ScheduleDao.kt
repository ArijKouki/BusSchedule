package com.gl4.tp4.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.gl4.tp4.database.entities.Schedule

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule ORDER BY arrival_time")
    fun getAll(): List<Schedule>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName")
    fun getByStopName(stopName: String): List<Schedule>
}