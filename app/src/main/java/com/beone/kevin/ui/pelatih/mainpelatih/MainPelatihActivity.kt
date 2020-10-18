package com.beone.kevin.ui.pelatih.mainpelatih

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.beone.kevin.MainActivity
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import kotlinx.android.synthetic.main.activity_main_pelatih.*
import org.koin.android.ext.android.inject


class MainPelatihActivity : AppCompatActivity() {

    private val sharepreference: SharedPreferenceUtils by inject()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_profile -> {
                Navigation.findNavController(this, R.id.nav_host_pelatih_fragment)
                    .navigate(R.id.item_profile)
                return true
            }
            R.id.item_logout -> {
                val moveIntent = Intent(this@MainPelatihActivity, MainActivity::class.java)
                sharepreference.removeUser()
                startActivity(moveIntent)
                return true
            }
            else -> return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_pelatih)

        setupViews()
    }

    fun setupViews(){
        // Finding the Navigation Controller
        val navController = findNavController(R.id.nav_host_pelatih_fragment)

        // Setting Navigation Controller with the BottomNavigationView
        nav_view_coach.setupWithNavController(navController)

        // Setting Up ActionBar with Navigation Controller
        // Pass the IDs of top-level destinations in AppBarConfiguration
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.navigation_coachhome,
                R.id.navigation_coachschedule,
                R.id.navigation_coachscore,
                R.id.navigation_testattendance,
                R.id.item_profile,
                R.id.coachDetailScheduleFragment,
                R.id.selectTkiForTrainingFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}