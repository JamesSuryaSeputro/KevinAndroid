package com.beone.kevin.ui.login


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.beone.kevin.R
import kotlinx.android.synthetic.main.logins_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.Type

class LoginsFragment : Fragment() {

    companion object {
        private const val TAG = "LoginsFragment"
        fun newInstance() = LoginsFragment()
    }

    private val vm: LoginsViewModel by viewModel<LoginsViewModel>()
    private lateinit var arrayAdapter: ArrayAdapter<TypeLoginEnum>
    private lateinit var typeLoginEnum: TypeLoginEnum

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.logins_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(LoginsViewModel::class.java)
        Log.d(TAG, "onActivityCreated: ${vm.getRetrofitServiceHash()}")

        btn_register.setOnClickListener {
            view?.findNavController()?.navigate(R.id.register_action)
        }

        btn_login.setOnClickListener{
            when(spr_categorylogin.selectedItemPosition){
                TypeLoginEnum.PILIH_LOGIN.jenis -> {
                    Toast.makeText(this.requireContext(), "Pilih Jenis Login", Toast.LENGTH_SHORT).show()
                }
                TypeLoginEnum.TKI.jenis -> {
                    Log.d(TAG, "onActivityCreated: ${TypeLoginEnum.TKI.type}")
                }
                TypeLoginEnum.PELATIH.jenis -> {
                    Log.d(TAG, "onActivityCreated: ${TypeLoginEnum.PELATIH.type}")
                }
                TypeLoginEnum.PEGAWAI.jenis ->
                {
                    Log.d(TAG, "onActivityCreated: ${TypeLoginEnum.PEGAWAI.type}")
                }
                else -> { // Note the block
                    Log.w(TAG, "onItemClick: Not Have Object Selected On Enum" )
                }
            }
        }

        arrayAdapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            TypeLoginEnum.values()
        )
        spr_categorylogin.dropDownVerticalOffset = 20
        spr_categorylogin.adapter = arrayAdapter

    }



}