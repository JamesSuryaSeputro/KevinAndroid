package com.beone.kevin.ui.pelatih.adddetailschedulepelatih

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.beone.kevin.R
import com.beone.kevin.ui.pelatih.DayEnum
import com.beone.kevin.ui.pelatih.schedulepelatih.SchedulePelatihViewModel
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
    //private lateinit var idJadwal: String
//    val args: AddDetailSchedulePelatihDialogFragmentArgs by navArgs<AddDetailSchedulePelatihDialogFragmentArgs>()
    private val addDetailSchedulePelatihViewModel: SchedulePelatihViewModel by sharedViewModel()

    val now = Calendar.getInstance()
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
        //idJadwal = args.idJadwal
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

        addDetailSchedulePelatihViewModel.initDialog().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
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
//                    addDetailSchedulePelatihViewModel.addDetailSchedule(
//                        //idJadwal,
//                        checkSpinnerDay(),
//                        tv_pickeddate.text.toString(),
//                        tv_pickedstarttime.text.toString(),
//                        tv_pickedendtime.text.toString()
//                    )
                }
            }
        }

        pick_date.setOnClickListener {
            DATE_TIME_DIALOG = 1
            dpd.show(activity?.supportFragmentManager!!, DATE_PICKER_TAG)
        }

        pick_starttime.setOnClickListener {
            DATE_TIME_DIALOG = 3
            tpd.show(activity?.supportFragmentManager!!, START_TIME_PICKER_TAG)
        }

        pick_endtime.setOnClickListener {
            DATE_TIME_DIALOG = 4
            tpd.show(activity?.supportFragmentManager!!, END_TIME_PICKER_TAG)
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
        if(DATE_TIME_DIALOG==1) {
            tv_pickeddate.text = dateFormat.format(now.time).toString()
        }
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        now.set(Calendar.HOUR_OF_DAY, hourOfDay)
        now.set(Calendar.MINUTE, minute)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        when(DATE_TIME_DIALOG){
            3 -> tv_pickedstarttime.text = timeFormat.format(now.time).toString()
            4 -> tv_pickedendtime.text = timeFormat.format(now.time).toString()
        }
    }
}