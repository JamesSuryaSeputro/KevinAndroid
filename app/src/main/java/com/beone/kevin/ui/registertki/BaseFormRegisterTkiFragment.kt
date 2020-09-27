package com.beone.kevin.ui.registertki

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.beone.kevin.R
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.base_form_register_tki_fragment.*
import java.text.SimpleDateFormat
import java.util.*


abstract class BaseFormRegisterTkiFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var genderArrayAdapter: ArrayAdapter<TypeGenderEnum>
    val now = Calendar.getInstance()
    private val dpd: DatePickerDialog = DatePickerDialog.newInstance(
        this@BaseFormRegisterTkiFragment,
        now[Calendar.YEAR],
        now[Calendar.MONTH],
        now[Calendar.DAY_OF_MONTH]
    )

    companion object {
        private const val DATE_PICKER_TAG = "DatePicker"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.base_form_register_tki_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_title.text = setTitleFragment()
        initSpinner()
        ll_register.setOnClickListener { hideKeyboard(ll_register) }
        pick_date.setOnClickListener {
            dpd.show(activity?.supportFragmentManager!!, DATE_PICKER_TAG)
        }
    }

    abstract fun initUi()
    abstract fun setTitleFragment(): String

    fun initSpinner() {
        genderArrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            TypeGenderEnum.values()
        )

        spnr_gender.adapter = genderArrayAdapter
    }

    fun hideKeyboard(view: View) {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun checkSpinnerGender(): TypeGenderEnum {
        when (spnr_gender.selectedItem) {
            TypeGenderEnum.Jenis_Kelamin -> {
                Toast.makeText(requireContext(), "Pilih jenis kelamin", Toast.LENGTH_SHORT)
                    .show()

                return TypeGenderEnum.Jenis_Kelamin
            }
            TypeGenderEnum.Pria -> {

                return TypeGenderEnum.Pria
            }
            TypeGenderEnum.Wanita -> {

                return TypeGenderEnum.Wanita
            }
            else -> throw Exception("Wrong type for gender")
        }
    }

    override fun onResume() {
        super.onResume()
        val dpd =
            activity?.supportFragmentManager!!.findFragmentByTag(DATE_PICKER_TAG) as DatePickerDialog?
        if (dpd != null) dpd.onDateSetListener = this
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        now.set(year, monthOfYear, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        tv_dateofbirth.text = dateFormat.format(now.time).toString()
    }
}