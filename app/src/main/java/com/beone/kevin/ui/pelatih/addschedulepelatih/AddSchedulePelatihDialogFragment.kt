package com.beone.kevin.ui.pelatih.addschedulepelatih

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.ui.pelatih.DayEnum
import com.beone.kevin.ui.pelatih.SubjectEnum
import com.beone.kevin.ui.pelatih.schedulepelatih.SchedulePelatihViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.add_schedule_pelatih_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ClassCastException

class AddSchedulePelatihDialogFragment : BottomSheetDialogFragment() {

    private lateinit var subjectArrayAdapter: ArrayAdapter<SubjectEnum>
    private lateinit var dayArrayAdapter: ArrayAdapter<DayEnum>
    companion object {
        private const val TAG = "AddSchedulePelatihDialog"
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

        dayArrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            DayEnum.values()
        )

        spnr_subject.adapter = subjectArrayAdapter
        spnr_hari.adapter = dayArrayAdapter

        addSchedulePelatihViewModel.initDialog().observe(viewLifecycleOwner, Observer {
            if (it.status.equals(1)) {
                Toast.makeText(requireContext(), "Success Add jadwal", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        })

        btn_add_jadwal.setOnClickListener {
            addSchedulePelatihViewModel.addSchedule(
                sharedPreferenceUtils.getIdUser,
                checkSpinnerSubject(),
                checkSpinnerDay()
            )
        }

    }

    fun checkSpinnerSubject(): SubjectEnum {
        when (spnr_subject.selectedItem) {
            SubjectEnum.Bahasa -> {
                return SubjectEnum.Bahasa
            }
            SubjectEnum.Tata_Krama -> {
                return SubjectEnum.Tata_Krama
            }
            else -> throw Exception("Wrong type for Subject Enum")
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

}