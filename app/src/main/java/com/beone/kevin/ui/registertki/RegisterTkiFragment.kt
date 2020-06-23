package com.beone.kevin.ui.registertki

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beone.kevin.CustomImageUtils
import com.beone.kevin.R
import com.beone.kevin.remote.model.RegisterTKIModel
import kotlinx.android.synthetic.main.base_form_register_tki_fragment.*
import org.koin.android.ext.android.inject


class RegisterTkiFragment : BaseFormRegisterTkiFragment() {

    companion object {
        fun newInstance() = RegisterTkiFragment()
        private const val TAG = "RegisterTkiFragment"
        private const val RESULT_PASFOTO = 12
        private const val RESULT_TTD = 13
    }

    private val vm: RegisterTkiViewModel by inject()
    private lateinit var bitmap: Bitmap
    private lateinit var bitmap2: Bitmap

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

            Log.d(TAG, "btnSignup: clicked")
            val pasfoto = CustomImageUtils.BitmapToString(bitmap)
            val ttdfoto = CustomImageUtils.BitmapToString(bitmap2)
            vm.registerTki(
                RegisterTKIModel(
                    edt_address.text.toString(),
                    edt_otherlanguage.text.toString(),
                    edt_weight.text.toString(),
                    edt_field.text.toString(),
                    booleanColorBlindEnum.ordinal.toString(),
                    healthEnum.ordinal.toString(),
                    skillEnglishEnum.ordinal.toString(),
                    genderEnum.ordinal.toString(),
                    edt_nationality.text.toString(),
                    edt_checkuprs.text.toString(),
                    edt_joblocation1.text.toString(),
                    edt_joblocation2.text.toString(),
                    edt_joblocation3.text.toString(),
                    skillMandarinEnum.ordinal.toString(),
                    edt_right.text.toString(),
                    edt_left.text.toString(),
                    edt_nameedu.text.toString(),
                    edt_noktp.text.toString(),
                    edt_nopassport.text.toString(),
                    edt_noalternative.text.toString(),
                    pasfoto,
                    edt_password.text.toString(),
                    edt_job1.text.toString(),
                    edt_job2.text.toString(),
                    edt_job3.text.toString(),
                    booleanMandarinEduEnum.ordinal.toString(),
                    edt_lasteducation.text.toString(),
                    edt_workexperience1.text.toString(),
                    edt_workexperience2.text.toString(),
                    edt_enddate1.text.toString(),
                    edt_enddate2.text.toString(),
                    edt_startdate1.text.toString(),
                    edt_startdate2.text.toString(),
                    edt_sector1.text.toString(),
                    edt_sector2.text.toString(),
                    edt_sector3.text.toString(),
                    edt_jobcertificate1.text.toString(),
                    edt_jobcertificate2.text.toString(),
                    edt_jobcertificate3.text.toString(),
                    maritalStatusEnum.ordinal.toString(),
                    edt_dateofbirth.text.toString(),
                    edt_checkupdate.text.toString(),
                    edt_startedudate.text.toString(),
                    edt_place.text.toString(),
                    edt_height.text.toString(),
                    ttdfoto,
                    edt_age.text.toString(),
                    edt_wage.text.toString(),
                    edt_username.text.toString()
                )
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

        vm.initDataRegisterTki().observe(viewLifecycleOwner, Observer {
            if (it.status.equals("1")) {
                Toast.makeText(this.requireContext(), "Sukses ", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        btn_pasfoto.setOnClickListener {
            // TODO: 19/06/20 add pick picture
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, RESULT_PASFOTO)
        }
        btn_ttd.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, RESULT_TTD)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(
            TAG, "onActivityResult: ${requestCode} ${resultCode} ${data?.data}"
        )
        when (requestCode) {
            RESULT_PASFOTO -> {
                bitmap =
                    MediaStore.Images.Media.getBitmap(this.context?.contentResolver, data?.data)
                if (!bitmap.equals("")) {
                    tv_filepf.setText("terisi")
                }
            }
            RESULT_TTD -> {
                bitmap2 =
                    MediaStore.Images.Media.getBitmap(this.context?.contentResolver, data?.data)
                if (!bitmap2.equals("")) {
                    tv_filettd.setText("terisi")
                }
            }
        }
    }
}
