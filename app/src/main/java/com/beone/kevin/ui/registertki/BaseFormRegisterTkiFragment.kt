package com.beone.kevin.ui.registertki

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
import androidx.navigation.fragment.navArgs
import com.beone.kevin.R
import kotlinx.android.synthetic.main.base_form_register_tki_fragment.*
import kotlinx.android.synthetic.main.base_form_register_tki_fragment.view.*


abstract class BaseFormRegisterTkiFragment : Fragment() {
    private lateinit var genderArrayAdapter: ArrayAdapter<TypeGenderEnum>
   // private var isProfil: Boolean = false
//    val args: RegisterTkiFragmentArgs by navArgs<RegisterTkiFragmentArgs>()

//    open fun getProfil(): Boolean {
//        return isProfil
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.base_form_register_tki_fragment, container, false)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        isProfil = args.profil
////    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_title.text = setTitleFragment()
        initSpinner()
        ll_register.setOnClickListener { hideKeyboard(ll_register) }

//        Log.d("a", "isProfil: " + isProfil)
//        if (isProfil) {
//            tv_title.text = getString(R.string.profil)
//            view.ll_pasfoto.visibility = View.GONE
//            view.ll_ttd.visibility = View.GONE
//            btn_signup.visibility = View.GONE
//            tv_registered.visibility = View.GONE
//            tv_tologin.visibility = View.GONE
//        } else {
            img_pasfoto.visibility = View.GONE
//        }
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
}