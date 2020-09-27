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
import kotlinx.android.synthetic.main.base_form_register_coach_fragment.*
import kotlinx.android.synthetic.main.base_form_register_tki_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class RegisterTkiFragment : BaseFormRegisterTkiFragment() {

    companion object {
        fun newInstance() = RegisterTkiFragment()
        private const val TAG = "RegisterTkiFragment"
        private const val RESULT_PASFOTO = 12
        private const val RESULT_TTD = 13
    }

    private val vm: RegisterTkiViewModel by sharedViewModel()
    private var bitmapPf: Bitmap? = null
    private var bitmapTtd: Bitmap? = null

    override fun initUi() {
        var genderEnum: TypeGenderEnum

        btn_signup.setOnClickListener {
            Log.d(TAG, "btnSignup: clicked")
            genderEnum = checkSpinnerGender()
            Log.d(
                TAG, "initUI: gender: ${genderEnum} ordinal value: ${genderEnum.ordinal} \n"
            )

            if (genderEnum.ordinal != 0) {
                var pasfoto: String? = ""
                var fotoTtd: String? = ""
                if (edt_password.text.toString() != edt_confirmpassword.text.toString()){
                    Toast.makeText(this.requireContext(), "Password tidak sama", Toast.LENGTH_SHORT).show()
                } else if (edt_address.text.toString() != "" && edt_nationality.text.toString() != "" && edt_name.text.toString() != ""
                    && edt_noktp.text.toString() != "" && edt_nopassport.text.toString() != "" && edt_nohp.text.toString() != ""
                    && edt_password.text.toString() != "" && edt_place.text.toString() != "" && edt_username.text.toString() != ""
                    && bitmapPf != null && bitmapTtd != null
                ) {
                    pasfoto = bitmapPf?.let { it1 -> CustomImageUtils.BitmapToString(it1) }
                    fotoTtd = bitmapTtd?.let { it1 -> CustomImageUtils.BitmapToString(it1) }
                    vm.registerTki(
                        edt_username.text.toString(),
                        edt_password.text.toString(),
                        edt_name.text.toString(),
                        edt_nopassport.text.toString(),
                        edt_noktp.text.toString(),
                        edt_place.text.toString(),
                        tv_dateofbirth.text.toString(),
                        edt_nationality.text.toString(),
                        genderEnum.ordinal.toString(),
                        edt_address.text.toString(),
                        edt_nohp.text.toString(),
                        pasfoto!!,
                        fotoTtd!!
                    )
                } else {
                    Log.d(TAG, "masih kosong ")
                    Toast.makeText(
                        this.requireContext(),
                        "Masih ada yang kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun setTitleFragment(): String = "Register"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_tologin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginsFragment)
        }
        initUi()

        vm.initDataRegisterTki().observe(viewLifecycleOwner, Observer {
            if (it.status.equals(1)) {
                Toast.makeText(this.requireContext(), "Sukses", Toast.LENGTH_SHORT).show()
                clearAll()
            }
        })

        btn_pasfoto.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(
                Intent.createChooser(intent, "Select image"),
                RESULT_PASFOTO
            )
        }

        btn_ttd.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(
                Intent.createChooser(intent, "Select image"),
                RESULT_TTD
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(
            TAG, "onActivityResult: ${requestCode} ${resultCode} ${data?.data}"
        )
        if (data != null) {
            when (requestCode) {
                RESULT_PASFOTO -> {
                    bitmapPf =
                        MediaStore.Images.Media.getBitmap(this.context?.contentResolver, data?.data)
                    if (bitmapPf != null) {
                        tv_filepf.setText("terisi")
                    }
                }
                RESULT_TTD -> {
                    bitmapTtd =
                        MediaStore.Images.Media.getBitmap(this.context?.contentResolver, data?.data)
                    if (bitmapTtd != null) {
                        tv_filettd.setText("terisi")
                    }
                }
            }
        }
    }

    fun clearAll() {
        edt_address.setText("")
        edt_nationality.setText("")
        edt_name.setText("")
        edt_noktp.setText("")
        edt_nopassport.setText("")
        edt_nohp.setText("")
        tv_filepf.setText("")
        bitmapPf = null
        edt_password.setText("")
        tv_dateofbirth.setText("")
        edt_place.setText("")
        tv_filettd.setText("")
        bitmapTtd = null
        edt_username.setText("")
    }
}
