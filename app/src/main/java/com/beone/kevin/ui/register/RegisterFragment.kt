package com.beone.kevin.ui.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.beone.kevin.R
import kotlinx.android.synthetic.main.base_form_register_fragment.*
import org.koin.android.ext.android.inject


class RegisterFragment : BaseFormRegisterFragment() {

    companion object {
        fun newInstance() = RegisterFragment()
        private const val TAG = "RegisterFragment"
    }

    private val viewModel: RegisterViewModel by inject<RegisterViewModel>()

    override fun initUi() {
        var genderEnum: TypeGenderEnum
        var maritalStatusEnum: TypeMaritalStatusEnum
        var booleanColorBlindEnum: TypeBooleanEnum
        var booleanMandarinEduEnum: TypeBooleanEnum
        var skillMandarinEnum: TypeSkillEnum
        var skillEnglishEnum: TypeSkillEnum
        var healthEnum: TypeHealthEnum

        btn_signup.setOnClickListener() {
                    genderEnum = checkSpinnerGender()
                    maritalStatusEnum = checkSpinnerMaritalStatus()
                    booleanColorBlindEnum = checkSpinnerBooleanColorBlind()
                    booleanMandarinEduEnum = checkSpinnerBooleanMandarinEdu()
                    skillMandarinEnum = checkSpinnerSkillMandarin()
                    skillEnglishEnum = checkSpinnerSkillEnglish()
                    healthEnum = checkSpinnerHealth()

            Log.d(
                TAG, "initUI: gender: ${genderEnum} ordinal value: ${genderEnum.ordinal} \n" +
                        "initUI: maritalstatus: ${maritalStatusEnum} ordinal value: ${maritalStatusEnum.ordinal} \n" +
                        "initUI: boolean color: ${booleanColorBlindEnum} ordinal value: ${booleanColorBlindEnum.ordinal} \n" +
                        "initUI: boolean mandarin: ${booleanMandarinEduEnum} ordinal value: ${booleanMandarinEduEnum.ordinal} \n" +
                        "initUI: skill mandarin: ${skillMandarinEnum} ordinal value: ${skillMandarinEnum.ordinal} \n" +
                        "initUI: skill english: ${skillEnglishEnum} ordinal value: ${skillEnglishEnum.ordinal} \n" +
                        "initUI: health: ${healthEnum} ordinal value: ${healthEnum.ordinal} \n"
            )
        }
    }

    override fun setTitleFragment(): String = "Register 2"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_tologin.setOnClickListener {
           findNavController().navigate(R.id.action_registerFragment_to_loginsFragment)
        }

        initUi()
    }
}

