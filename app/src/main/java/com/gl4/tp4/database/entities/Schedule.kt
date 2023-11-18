package com.gl4.tp4.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "stop_name")
    var stopName: String="",
    @ColumnInfo(name = "arrival_time")
    var arrivalTime: Long=0
)
