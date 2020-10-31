package com.beone.kevin.ui.user.scoreview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.ui.user.scheduleuser.ScheduleUserAdapter
import com.beone.kevin.ui.user.scheduleuser.ScheduleUserFragment
import com.beone.kevin.ui.user.scheduleuser.ScheduleUserViewModel
import kotlinx.android.synthetic.main.schedule_user_fragment.*
import kotlinx.android.synthetic.main.score_view_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScoreViewFragment : Fragment() {

    companion object {
        fun newInstance() = ScoreViewFragment()
        private const val TAG = "ScoreViewFragment"
    }

    private val viewModel: ScheduleUserViewModel by sharedViewModel()
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject()
    private val adapter: ScoreViewAdapter = ScoreViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.score_view_fragment, container, false)
    }

    @ExperimentalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.dataJadwal.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()) {
                adapter.showScoreUser(it)
                tv_noscoreuser.visibility = View.GONE
                rcv_jadwalnilaiuser.visibility = View.VISIBLE
            } else {
                rcv_jadwalnilaiuser.visibility = View.GONE
                tv_noscoreuser.visibility = View.VISIBLE
            }
        })

        viewModel.getAllDataSchedule(sharedPreferenceUtils.getIdUser)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcv_jadwalnilaiuser.adapter = adapter
    }
}