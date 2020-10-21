package com.beone.kevin.ui.user.scheduleuser

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.ui.pelatih.schedulepelatih.JadwalAdapter
import kotlinx.android.synthetic.main.schedule_pelatih_fragment.*
import kotlinx.android.synthetic.main.schedule_user_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ScheduleUserFragment : Fragment() {

    companion object {
        fun newInstance() = ScheduleUserFragment()
        private const val TAG = "ScheduleUserFragment"
    }

    private val viewModel: ScheduleUserViewModel by sharedViewModel()
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject()
    private val adapter: ScheduleUserAdapter = ScheduleUserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.schedule_user_fragment, container, false)
    }

    @ExperimentalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.dataJadwal.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "observer")
            if(it.isNotEmpty()) {
                adapter.showScheduleUser(it)
                tv_noscheduleuser.visibility = View.GONE
                rcv_jadwaluser.visibility = View.VISIBLE
            } else {
                rcv_jadwaluser.visibility = View.GONE
                tv_noscheduleuser.visibility = View.VISIBLE
            }
        })

        viewModel.getAllDataSchedule(sharedPreferenceUtils.getIdUser)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcv_jadwaluser.adapter = adapter
    }
}