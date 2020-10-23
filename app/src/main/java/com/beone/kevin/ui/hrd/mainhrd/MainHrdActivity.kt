package com.beone.kevin.ui.hrd.mainhrd

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.beone.kevin.MainActivity
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import kotlinx.android.synthetic.main.activity_main_hrd.*
import org.koin.android.ext.android.inject


class MainHrdActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val sharepreference: SharedPreferenceUtils by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_hrd)

        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_hrd_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_hrdhome,
                R.id.navigation_addcoach,
                R.id.navigation_addhrd,
                R.id.navigation_sendtki,
                R.id.navigation_checkpayment,
                R.id.navigation_checkdocument,
                R.id.navigation_datatki,
                R.id.detailDataTkiFragment,
                R.id.approveUserPaymentFragment,
                R.id.approveUserDocumentFragment,
                R.id.item_profile
            ), drawer_layout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view_hrd.setupWithNavController(navController)

        val mHeaderView = nav_view_hrd.getHeaderView(0)
        val tvNamaHrd = mHeaderView.findViewById(R.id.namaHrd) as TextView
        tvNamaHrd.text = sharepreference.getNamaUser
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_profile -> {
                Navigation.findNavController(this, R.id.nav_host_hrd_fragment)
                    .navigate(R.id.item_profile)
                true
            }
            R.id.item_logout -> {
                val moveIntent = Intent(this@MainHrdActivity, MainActivity::class.java)
                sharepreference.removeUser()
                startActivity(moveIntent)
                true
            }
            else -> false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_hrd_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}