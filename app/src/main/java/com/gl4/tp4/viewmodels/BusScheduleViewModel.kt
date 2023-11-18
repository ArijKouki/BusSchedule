package com.gl4.tp4.viewmodels

import androidx.lifecycle.ViewModel
import com.gl4.tp4.database.daos.ScheduleDao
import com.gl4.tp4.database.entities.Schedule

class BusScheduleViewModel(private val scheduleDao: ScheduleDao) : ViewModel() {
    fun fullSchedule(): List<Schedule> = scheduleDao.getAll()
    fun scheduleForStopName(name: String): List<Schedule> = scheduleDao.getByStopName(name)
}