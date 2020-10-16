package com.beone.kevin.ui.pelatih.addschedulepelatih

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.ui.pelatih.SubjectEnum
import com.beone.kevin.ui.pelatih.schedulepelatih.SchedulePelatihViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.add_schedule_pelatih_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddSchedulePelatihDialogFragment : BottomSheetDialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var subjectArrayAdapter: ArrayAdapter<SubjectEnum>

    val now = Calendar.getInstance()
    private val dpd: DatePickerDialog = DatePickerDialog.newInstance(
        this@AddSchedulePelatihDialogFragment,
        now[Calendar.YEAR],
        now[Calendar.MONTH],
        now[Calendar.DAY_OF_MONTH]
    )

    companion object {
        private const val TAG = "AddSchedulePelatihDialog"
        private const val START_DATE_PICKER_TAG = "StartDatePicker"
        private const val END_DATE_PICKER_TAG = "EndDatePicker"
        private var DATE_DIALOG = 0
    }

    private val addSchedulePelatihViewModel: SchedulePelatihViewModel by sharedViewModel<SchedulePelatihViewModel>()
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject<SharedPreferenceUtils>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_schedule_pelatih_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subjectArrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            SubjectEnum.values()
        )

        spnr_subject.adapter = subjectArrayAdapter

        addSchedulePelatihViewModel.initDialog().observe(viewLifecycleOwner, Observer {
            if (it.status.equals(1)) {
                Toast.makeText(requireContext(), "Success Add jadwal", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        })

        btn_add_jadwal.setOnClickListener {
            when {
                tv_pickedstartdate.text.toString() == "" -> {
                    Toast.makeText(requireContext(), "Belum memilih tanggal mulai", Toast.LENGTH_SHORT).show()
                }
                tv_pickedenddate.text.toString() == "" -> {
                    Toast.makeText(requireContext(), "Belum memilih tanggal selesai", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    addSchedulePelatihViewModel.addSchedule(
                        sharedPreferenceUtils.getIdUser,
                        checkSpinnerSubject(),
                        tv_pickedstartdate.text.toString(),
                        tv_pickedenddate.text.toString()
                    )
                }
            }
        }

        pick_startdate.setOnClickListener {
            DATE_DIALOG = 1
            dpd.show(activity?.supportFragmentManager!!, START_DATE_PICKER_TAG)
        }

        pick_enddate.setOnClickListener {
            DATE_DIALOG = 2
            dpd.show(activity?.supportFragmentManager!!, END_DATE_PICKER_TAG)
        }
    }

    fun checkSpinnerSubject(): SubjectEnum {
        when (spnr_subject.selectedItem) {
            SubjectEnum.Bahasa_Mandarin -> {
                return SubjectEnum.Bahasa_Mandarin
            }
            SubjectEnum.Tata_Krama -> {
                return SubjectEnum.Tata_Krama
            }
            else -> throw Exception("Wrong type for Subject Enum")
        }
    }

    override fun onResume() {
        super.onResume()
        val dpdStart =
            activity?.supportFragmentManager!!.findFragmentByTag(START_DATE_PICKER_TAG) as DatePickerDialog?
        val dpdEnd =
            activity?.supportFragmentManager!!.findFragmentByTag(END_DATE_PICKER_TAG) as DatePickerDialog?
        if (dpdStart != null) dpdStart.onDateSetListener = this
        if (dpdEnd != null) dpdEnd.onDateSetListener = this
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        now.set(year, monthOfYear, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        when(DATE_DIALOG){
            1 -> tv_pickedstartdate.text = dateFormat.format(now.time).toString()
            2 -> tv_pickedenddate.text = dateFormat.format(now.time).toString()
        }
    }
}