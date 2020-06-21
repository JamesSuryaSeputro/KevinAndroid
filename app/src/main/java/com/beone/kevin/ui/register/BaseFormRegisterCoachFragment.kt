package com.beone.kevin.ui.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.beone.kevin.R
import kotlinx.android.synthetic.main.base_form_register_coach_fragment.*
import kotlinx.android.synthetic.main.base_form_register_fragment.*

abstract class BaseFormRegisterCoachFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.base_form_register_coach_fragment, container, false)
    }

    private lateinit var viewModel: RegisterCoachViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ll_registercoach.setOnClickListener { hideKeyboard(ll_register) }
    }

    fun hideKeyboard(view: View) {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}