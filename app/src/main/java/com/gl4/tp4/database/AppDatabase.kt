package com.gl4.tp4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gl4.tp4.database.daos.ScheduleDao
import com.gl4.tp4.database.entities.Schedule

@Database(entities = [Schedule::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).createFromAsset("database/bus_schedule.db").build()
                INSTANCE = instance
                instance
            }
        }
    }
}
