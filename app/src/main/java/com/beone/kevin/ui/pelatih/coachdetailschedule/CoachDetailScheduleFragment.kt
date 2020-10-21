package com.beone.kevin.ui.pelatih.coachdetailschedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.beone.kevin.R
import kotlinx.android.synthetic.main.coach_detail_schedule_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CoachDetailScheduleFragment : Fragment(), OnDetailClick {

    companion object {
        fun newInstance() = CoachDetailScheduleFragment()
    }

    private val viewModel: CoachDetailScheduleViewModel by sharedViewModel()
    private lateinit var mIdJadwal: String
    private lateinit var detailName: String
    private lateinit var detailStartDate: String
    private lateinit var detailEndDate: String
    private var detailScore: Boolean = false
    val args: CoachDetailScheduleFragmentArgs by navArgs()
    private val adapter: CoachDetailScheduleAdapter = CoachDetailScheduleAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mIdJadwal = args.idJadwal
        detailName = args.detailname
        detailStartDate = args.detailstartdate
        detailEndDate = args.detailenddate
        detailScore = args.scoredetail
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

        if (!detailScore) {
            fab_adddetailjadwal.setOnClickListener {

                val action =
                    CoachDetailScheduleFragmentDirections.actionCoachDetailScheduleFragmentToAddDetailSchedulePelatihDialogFragment(
                        mIdJadwal, detailStartDate, detailEndDate
                    )
                this.findNavController().navigate(action)

                btn_pilihtki.setOnClickListener {
                    val action =
                        CoachDetailScheduleFragmentDirections.actionCoachDetailScheduleFragmentToSelectTkiForTrainingFragment(
                            mIdJadwal
                        )
                    this.findNavController().navigate(action)
                }
            }
        } else {
            fab_adddetailjadwal.visibility = View.GONE
            btn_pilihtki.visibility = View.GONE
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.initData().observe(viewLifecycleOwner, Observer {
            adapter.swapData(it)
        })

        viewModel.getDataDetailJadwal(mIdJadwal)

        swiperefresh.setOnRefreshListener {
            viewModel.getDataDetailJadwal(mIdJadwal)
            swiperefresh.isRefreshing = false
        }
    }

    override fun onDelete(id: String?) {
        Toast.makeText(requireContext(), "Delete Item ${id}", Toast.LENGTH_SHORT).show()
        viewModel.deleteDetailData(mIdJadwal, id)
    }

    override fun onDetail(id: String?) {
        val action =
            CoachDetailScheduleFragmentDirections.actionCoachDetailScheduleFragmentToAddNilaiFragment(
                id.toString()
            )
        this.findNavController().navigate(action)
    }
}