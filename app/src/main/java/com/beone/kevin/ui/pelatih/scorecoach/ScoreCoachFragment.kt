package com.beone.kevin.ui.pelatih.scorecoach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.ui.pelatih.schedulepelatih.SchedulePelatihViewModel
import kotlinx.android.synthetic.main.score_coach_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ScoreCoachFragment : Fragment(), OnClickItem {

    companion object {
        fun newInstance() = ScoreCoachFragment
    }

    private val viewModel: SchedulePelatihViewModel by sharedViewModel<SchedulePelatihViewModel>()
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject<SharedPreferenceUtils>()
    private val adapter: ScoreCoachAdapter = ScoreCoachAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.score_coach_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcv_jadwalscore.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.initData().observe(viewLifecycleOwner, Observer {
            adapter.swapData(it)
        })

        viewModel.getData(sharedPreferenceUtils.getIdUser)
    }


    override fun onDetail(id: String?, name: String?, startdate: String?, enddate: String?) {
        if (id != null) {
            val action =
                ScoreCoachFragmentDirections.actionNavigationCoachscoreToCoachDetailScheduleFragment(
                    id, name.toString(), startdate.toString(), enddate.toString(), true, false
                )
            this.findNavController().navigate(action)
        }
    }
}