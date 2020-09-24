package com.beone.kevin.ui.user.mainuser

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.ui.user.homeuser.HomeUserFragment
import com.beone.kevin.ui.user.scheduleuser.ScheduleUserFragment
import com.beone.kevin.ui.user.scoreview.ScoreViewFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main_user.*
import kotlinx.android.synthetic.main.main_user_fragment.*
import org.koin.android.ext.android.inject

class MainUserActivity : AppCompatActivity() {



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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_user)

        nav_view_user.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = HomeUserFragment.newInstance()
        addFragment(fragment)
    }


}