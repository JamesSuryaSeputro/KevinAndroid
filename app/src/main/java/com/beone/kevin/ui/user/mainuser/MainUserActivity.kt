package com.beone.kevin.ui.user.mainuser

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.beone.kevin.MainActivity
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.ui.user.homeuser.HomeUserFragment
import com.beone.kevin.ui.user.profileuser.ProfileUserFragment
import com.beone.kevin.ui.user.scheduleuser.ScheduleUserFragment
import com.beone.kevin.ui.user.scoreview.ScoreViewFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main_user.*
import org.koin.android.ext.android.inject


class MainUserActivity : AppCompatActivity() {

    private val sharepreference: SharedPreferenceUtils by inject()

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val fragment = HomeUserFragment.newInstance()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_schedule -> {
                    val fragment = ScheduleUserFragment()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_score -> {
                    val fragment = ScoreViewFragment()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }


    @SuppressLint("PrivateResource")
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    fun hideBottomNav(){
        val navView: BottomNavigationView = findViewById(R.id.nav_view_user)
        navView.visibility = View.GONE
    }

    fun showBottomNav(){
        val navView: BottomNavigationView = findViewById(R.id.nav_view_user)
        navView.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_profile -> {
                hideBottomNav()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content, ProfileUserFragment())
                    .addToBackStack(null)
                    .commit()
                return true
            }
            R.id.item_logout -> {
                val moveIntent = Intent(this@MainUserActivity, MainActivity::class.java)
                sharepreference.removeUser()
                startActivity(moveIntent)
                return true
            }
            else -> return true
        }
    }

    override fun onBackPressed() {
        showBottomNav()
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_user)

        showBottomNav()

        nav_view_user.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = HomeUserFragment.newInstance()
        addFragment(fragment)
    }
}