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
import kotlinx.android.synthetic.main.base_form_register_tki_fragment.*


abstract class BaseFormRegisterTkiFragment : Fragment() {
    private lateinit var genderArrayAdapter: ArrayAdapter<TypeGenderEnum>
    private lateinit var maritalStatusArrayAdapter: ArrayAdapter<TypeMaritalStatusEnum>
    private lateinit var booleanArrayAdapter: ArrayAdapter<TypeBooleanEnum>
    private lateinit var skillArrayAdapter: ArrayAdapter<TypeSkillEnum>
    private lateinit var healthArrayAdapter: ArrayAdapter<TypeHealthEnum>

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
    }

    abstract fun initUi()
    abstract fun setTitleFragment(): String

    fun initSpinner() {
        genderArrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            TypeGenderEnum.values()
        )
        maritalStatusArrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            TypeMaritalStatusEnum.values()
        )
        booleanArrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            TypeBooleanEnum.values()
        )
        skillArrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            TypeSkillEnum.values()
        )
        healthArrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            TypeHealthEnum.values()
        )

        spnr_gender.adapter = genderArrayAdapter
        spnr_maritalstatus.adapter = maritalStatusArrayAdapter
        spnr_colorblind.adapter = booleanArrayAdapter
        spnr_mandarinedu.adapter = booleanArrayAdapter
        spnr_mandarin.adapter = skillArrayAdapter
        spnr_english.adapter = skillArrayAdapter
        spnr_mcuresult.adapter = healthArrayAdapter
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

    fun checkSpinnerMaritalStatus(): TypeMaritalStatusEnum {
        when (spnr_maritalstatus.selectedItem) {
            TypeMaritalStatusEnum.Status_Pernikahan -> {
                Toast.makeText(requireContext(), "Pilih status pernikahan", Toast.LENGTH_SHORT)
                    .show()

                return TypeMaritalStatusEnum.Status_Pernikahan
            }
            TypeMaritalStatusEnum.Menikah -> {

                return TypeMaritalStatusEnum.Menikah
            }
            TypeMaritalStatusEnum.Lajang -> {

                return TypeMaritalStatusEnum.Lajang
            }
            else -> throw Exception("Wrong type for marital status")
        }
    }

    fun checkSpinnerBooleanColorBlind(): TypeBooleanEnum {
        when (spnr_colorblind.selectedItem) {
            TypeBooleanEnum.Pilih -> {
                Toast.makeText(requireContext(), "Buta warna belum dipilih", Toast.LENGTH_SHORT)
                    .show()

                return TypeBooleanEnum.Pilih
            }
            TypeBooleanEnum.Ya -> {

                return TypeBooleanEnum.Ya
            }
            TypeBooleanEnum.Tidak -> {

                return TypeBooleanEnum.Tidak
            }
            else -> throw Exception("Wrong type for boolean color blind")
        }
    }

    fun checkSpinnerBooleanMandarinEdu(): TypeBooleanEnum {
        when (spnr_mandarinedu.selectedItem) {
            TypeBooleanEnum.Pilih -> {
                Toast.makeText(requireContext(), "Pilih pendidikan bahasa Mandarin", Toast.LENGTH_SHORT)
                    .show()

                return TypeBooleanEnum.Pilih
            }
            TypeBooleanEnum.Ya -> {

                return TypeBooleanEnum.Ya
            }
            TypeBooleanEnum.Tidak -> {

                return TypeBooleanEnum.Tidak
            }
            else -> throw Exception("Wrong type for boolean mandarin edu")
        }
    }

    fun checkSpinnerSkillMandarin(): TypeSkillEnum{
        when (spnr_mandarin.selectedItem) {
            TypeSkillEnum.Pilih -> {
                Toast.makeText(requireContext(), "Pilih kemampuan bahasa Mandarin", Toast.LENGTH_SHORT)
                    .show()

                return TypeSkillEnum.Pilih
            }
            TypeSkillEnum.Kurang -> {
                return TypeSkillEnum.Kurang
            }
            TypeSkillEnum.Cukup -> {
                return TypeSkillEnum.Cukup
            }
            TypeSkillEnum.Baik -> {
                return TypeSkillEnum.Baik
            }
            else -> throw Exception("Wrong type for skill mandarin")
        }
    }

    fun checkSpinnerSkillEnglish(): TypeSkillEnum{
        when (spnr_english.selectedItem) {
            TypeSkillEnum.Pilih -> {
                Toast.makeText(requireContext(), "Pilih Kemampuan bahasa Inggris", Toast.LENGTH_SHORT)
                    .show()

                return TypeSkillEnum.Pilih
            }
            TypeSkillEnum.Kurang -> {
                return TypeSkillEnum.Kurang
            }
            TypeSkillEnum.Cukup -> {
                return TypeSkillEnum.Cukup
            }
            TypeSkillEnum.Baik -> {
                return TypeSkillEnum.Baik
            }
            else -> throw Exception("Wrong type for skill english")
        }
    }

    fun checkSpinnerHealth(): TypeHealthEnum{
        when (spnr_mcuresult.selectedItem) {
            TypeHealthEnum.Pilih -> {
                Toast.makeText(requireContext(), "Pilih hasil medical check up", Toast.LENGTH_SHORT)
                    .show()

                return TypeHealthEnum.Pilih
            }
            TypeHealthEnum.Sehat -> {

                return TypeHealthEnum.Sehat
            }
            TypeHealthEnum.Tidak -> {

                return TypeHealthEnum.Tidak
            }
            else -> throw Exception("Wrong type for health")
        }
    }
}