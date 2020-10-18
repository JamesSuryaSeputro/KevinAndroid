package com.beone.kevin.ui.pelatih.coachdetailschedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.beone.kevin.R
import kotlinx.android.synthetic.main.add_detail_schedule_pelatih_dialog_fragment.*
import kotlinx.android.synthetic.main.coach_detail_schedule_fragment.*
import kotlinx.android.synthetic.main.schedule_pelatih_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CoachDetailScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = CoachDetailScheduleFragment()
    }

    private val adapter: CoachDetailScheduleAdapter = CoachDetailScheduleAdapter()
    private val viewModel: CoachDetailScheduleViewModel by sharedViewModel()
    private lateinit var mIdJadwal: String
    private lateinit var detailName: String
    private lateinit var detailStartDate: String
    private lateinit var detailEndDate: String
    val args: CoachDetailScheduleFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mIdJadwal = args.idJadwal
        detailName = args.detailname
        detailStartDate = args.detailstartdate
        detailEndDate = args.detailenddate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.coach_detail_schedule_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcv_detailjadwal.adapter = adapter

        detailsubjectname.text = detailName
        detailstartdate.text = detailStartDate + " s/d "
        detailenddate.text = detailEndDate

        fab_adddetailjadwal.setOnClickListener {

            val action =
                CoachDetailScheduleFragmentDirections.actionCoachDetailScheduleFragmentToAddDetailSchedulePelatihDialogFragment(
                    mIdJadwal
                )
            this.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.initData().observe(viewLifecycleOwner, Observer {
            adapter.swapData(it)
        })

        viewModel.getDataDetailJadwal(mIdJadwal)
    }
}