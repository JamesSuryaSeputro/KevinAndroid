package com.beone.kevin.ui.registercoach

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beone.kevin.R
import kotlinx.android.synthetic.main.base_form_register_coach_fragment.*
import org.koin.android.ext.android.inject

class RegisterCoachFragment : BaseFormRegisterCoachFragment() {

    companion object {
        fun newInstance() =
            RegisterCoachFragment()
    }

    private val vm: RegisterCoachViewModel by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.initDataRegisterCoach().observe(viewLifecycleOwner, Observer {
            if (it.status.equals("1")) {
                Toast.makeText(this.requireContext(), "Sukses", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        btn_coachsignup.setOnClickListener {
            if (edt_coachusername.text.toString() != "" && edt_coachpassword.text.toString() != "" && edt_coachname.text.toString() != "") {
                vm.registerCoach(
                    edt_coachusername.text.toString(),
                    edt_coachpassword.text.toString(),
                    edt_coachname.text.toString()
                )
            } else {
                Toast.makeText(
                    this.requireContext(),
                    "Masih ada yang kosong",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

}