package com.beone.kevin.ui.hrd.homehrd

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import kotlinx.android.synthetic.main.home_hrd_fragment.*
import kotlinx.android.synthetic.main.home_pelatih_fragment.*
import kotlinx.android.synthetic.main.home_user_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeHrdFragment : Fragment() {

    companion object {
        fun newInstance() = HomeHrdFragment()
    }

    private val viewModel: HomeHrdViewModel by viewModel<HomeHrdViewModel>()

    private val sharepreference: SharedPreferenceUtils by inject<SharedPreferenceUtils>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_hrd_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(ContentValues.TAG, "getiduser: " + sharepreference.getIdUser)
        when (sharepreference.getIdUser?.length) {
            1 -> {
                id_hrd.text = "21" + sharepreference.getYearUser + "00" + sharepreference.getIdUser
            }
            2 -> {
                id_hrd.text = "21" + sharepreference.getYearUser + "0" + sharepreference.getIdUser
            }
            else -> {
                id_hrd.text = "21" + sharepreference.getYearUser + sharepreference.getIdUser
            }
        }
        tv_homenamehrd.text = sharepreference.getNamaUser
    }
}