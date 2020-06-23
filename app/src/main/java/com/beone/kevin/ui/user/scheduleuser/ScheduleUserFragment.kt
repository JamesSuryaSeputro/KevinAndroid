package com.beone.kevin.ui.user.scheduleuser

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beone.kevin.R

class ScheduleUserFragment : Fragment() {

    companion object {
        fun newInstance() = ScheduleUserFragment()
    }

    private lateinit var viewModel: ScheduleUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.schedule_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ScheduleUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}