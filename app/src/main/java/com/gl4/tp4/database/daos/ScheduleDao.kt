package com.gl4.tp4.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.gl4.tp4.database.entities.Schedule

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule ORDER BY arrivalTime")
    fun getAll(): List<Schedule>

    @Query("SELECT * FROM schedule WHERE stopName = :stopName")
    fun getByStopName(stopName: String): List<Schedule>
}