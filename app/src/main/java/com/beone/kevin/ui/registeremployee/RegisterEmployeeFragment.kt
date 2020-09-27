package com.beone.kevin.ui.registeremployee

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.base_form_register_coach_fragment.*
import kotlinx.android.synthetic.main.base_form_register_employee_fragment.*
import org.koin.android.ext.android.inject

class RegisterEmployeeFragment : BaseFormRegisterEmployeeFragment() {

    companion object {
        fun newInstance() =
            RegisterEmployeeFragment()
    }

    private val vm: RegisterEmployeeViewModel by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.initDataRegisterEmployee().observe(viewLifecycleOwner, Observer {
            if (it.status.equals(1)) {
                Toast.makeText(this.requireContext(), "Sukses", Toast.LENGTH_SHORT)
                    .show()
                clearAll()
            }
        })

        btn_employeesignup.setOnClickListener {
            if (edt_employeepassword.text.toString() != edt_employeeconfirmpassword.text.toString()){
                Toast.makeText(this.requireContext(), "Password tidak sama", Toast.LENGTH_SHORT).show()
            } else if (edt_employeeusername.text.toString() != "" && edt_employeepassword.text.toString() != ""
                && edt_employeename.text.toString() != "" && edt_nip.text.toString() != "") {
                vm.registerEmployee(
                    edt_employeeusername.text.toString(),
                    edt_employeepassword.text.toString(),
                    edt_employeename.text.toString(),
                    edt_nip.text.toString()
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

    fun clearAll(){
        edt_employeeusername.setText("")
        edt_employeepassword.setText("")
        edt_employeename.setText("")
        edt_nip.setText("")
    }
}