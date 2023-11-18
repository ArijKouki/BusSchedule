package com.gl4.tp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gl4.tp4.database.entities.Schedule
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() , BusStopAdapter.OnItemClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }



    override fun onItemClick(schedule: Schedule) {
        val navController = findNavController(R.id.nav_host_fragment)
        val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(schedule.stopName)
        navController.navigate(action)
    }


}