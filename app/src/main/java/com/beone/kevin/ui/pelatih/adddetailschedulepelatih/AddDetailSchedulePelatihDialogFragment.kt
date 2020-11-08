package com.beone.kevin.ui.pelatih.adddetailschedulepelatih

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.beone.kevin.R
import com.beone.kevin.ui.pelatih.DayEnum
import com.beone.kevin.ui.pelatih.coachdetailschedule.CoachDetailScheduleViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.add_detail_schedule_pelatih_dialog_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddDetailSchedulePelatihDialogFragment : BottomSheetDialogFragment(),
    DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    companion object {
        private const val TAG = "AddDetailSchedulePelatihDialog"
        private const val DATE_PICKER_TAG = "EndDatePicker"
        private const val START_TIME_PICKER_TAG = "StartTimePicker"
        private const val END_TIME_PICKER_TAG = "EndTimePicker"
        private var DATE_TIME_DIALOG = 0
    }

    private lateinit var dayArrayAdapter: ArrayAdapter<DayEnum>
    private lateinit var mIdJadwal: String
    private lateinit var detailStartDate: String
    private lateinit var detailEndDate: String
    val args: AddDetailSchedulePelatihDialogFragmentArgs by navArgs<AddDetailSchedulePelatihDialogFragmentArgs>()
    private val coachDetailScheduleViewModel: CoachDetailScheduleViewModel by sharedViewModel()

    private val now = Calendar.getInstance()
    private val dpd: DatePickerDialog = DatePickerDialog.newInstance(
        this@AddDetailSchedulePelatihDialogFragment,
        now[Calendar.YEAR],
        now[Calendar.MONTH],
        now[Calendar.DAY_OF_MONTH]
    )
    private val tpd: TimePickerDialog = TimePickerDialog.newInstance(
        this@AddDetailSchedulePelatihDialogFragment,
        now[Calendar.HOUR_OF_DAY],
        now[Calendar.MINUTE],
        true
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mIdJadwal = args.idJadwal
        detailStartDate = args.detailstartdate
        detailEndDate = args.detailenddate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.add_detail_schedule_pelatih_dialog_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dayArrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            DayEnum.values()
        )

        spnr_hari.adapter = dayArrayAdapter

        coachDetailScheduleViewModel.initDialog().observe(viewLifecycleOwner, Observer {
            if (it.status.equals(1)) {
                Toast.makeText(requireContext(), "Sukses add detail jadwal", Toast.LENGTH_SHORT)
                    .show()
                dismiss()
            }
        })

        btn_adddetailjadwal.setOnClickListener {
            when {
                tv_pickeddate.text.toString() == "" -> {
                    Toast.makeText(requireContext(), "Belum memilih tanggal", Toast.LENGTH_SHORT)
                        .show()
                }
                tv_pickedstarttime.text.toString() == "" -> {
                    Toast.makeText(requireContext(), "Belum memilih jam mulai", Toast.LENGTH_SHORT)
                        .show()
                }
                tv_pickedendtime.text.toString() == "" -> {
                    Toast.makeText(
                        requireContext(),
                        "Belum memilih jam selesai",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    coachDetailScheduleViewModel.addDetailSchedule(
                        mIdJadwal,
                        checkSpinnerDay(),
                        tv_pickeddate.text.toString(),
                        tv_pickedstarttime.text.toString(),
                        tv_pickedendtime.text.toString()
                    )
                }
            }
        }

        pick_date.setOnClickListener {
            DATE_TIME_DIALOG = 1
            val minCalendar = Calendar.getInstance()
            val min: List<String> = detailStartDate.split("-")
            val minYear = min[0].toInt()
            val minMonth = min[1].toInt() - 1
            val minDate = min[2].toInt()
            val maxCalendar = Calendar.getInstance()
            val max: List<String> = detailEndDate.split("-")
            val maxYear = max[0].toInt()
            val maxMonth = max[1].toInt() - 1
            val maxDate = max[2].toInt()
            minCalendar.set(minYear, minMonth, minDate)
            maxCalendar.set(maxYear, maxMonth, maxDate)
            dpd.minDate = minCalendar
            dpd.maxDate = maxCalendar
            if(!dpd.isAdded) {
                dpd.show(activity?.supportFragmentManager!!, DATE_PICKER_TAG)
            }
        }

        pick_starttime.setOnClickListener {
            DATE_TIME_DIALOG = 2
            if(!tpd.isAdded) {
                tpd.show(activity?.supportFragmentManager!!, START_TIME_PICKER_TAG)
            }
        }

        pick_endtime.setOnClickListener {
            DATE_TIME_DIALOG = 3
            if(!tpd.isAdded){
            tpd.show(activity?.supportFragmentManager!!, END_TIME_PICKER_TAG)
            }
        }
    }

    fun checkSpinnerDay(): DayEnum {
        when (spnr_hari.selectedItem) {
            DayEnum.senin -> {
                return DayEnum.senin
            }
            DayEnum.selasa -> {
                return DayEnum.selasa
            }
            DayEnum.rabu -> {
                return DayEnum.rabu
            }
            DayEnum.kamis -> {
                return DayEnum.kamis
            }
            DayEnum.jumat -> {
                return DayEnum.jumat
            }
            else -> throw Exception("Wrong type for Day Enum")
        }
    }

    override fun onResume() {
        super.onResume()
        val dpd =
            activity?.supportFragmentManager!!.findFragmentByTag(DATE_PICKER_TAG) as DatePickerDialog?
        val tpdStart =
            activity?.supportFragmentManager!!.findFragmentByTag(START_TIME_PICKER_TAG) as TimePickerDialog?
        val tpdEnd =
            activity?.supportFragmentManager!!.findFragmentByTag(END_TIME_PICKER_TAG) as TimePickerDialog?
        if (dpd != null) dpd.onDateSetListener = this
        if (tpdStart != null) tpdStart.onTimeSetListener = this
        if (tpdEnd != null) tpdEnd.onTimeSetListener = this
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        now.set(year, monthOfYear, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        if (DATE_TIME_DIALOG == 1) {
            tv_pickeddate.text = dateFormat.format(now.time).toString()
        }
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        now.set(Calendar.HOUR_OF_DAY, hourOfDay)
        now.set(Calendar.MINUTE, minute)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        when (DATE_TIME_DIALOG) {
            2 -> tv_pickedstarttime.text = timeFormat.format(now.time).toString()
            3 -> tv_pickedendtime.text = timeFormat.format(now.time).toString()
        }
    }
}