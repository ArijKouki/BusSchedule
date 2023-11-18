package com.gl4.tp4.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var stopName: String="",
    var arrivalTime: Int=0
)
