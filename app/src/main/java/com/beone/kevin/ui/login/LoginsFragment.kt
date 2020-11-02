package com.beone.kevin.ui.login


import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beone.kevin.MainActivity
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.ui.hrd.mainhrd.MainHrdActivity
import com.beone.kevin.ui.pelatih.mainpelatih.MainPelatihActivity
import com.beone.kevin.ui.user.mainuser.MainUserActivity
import kotlinx.android.synthetic.main.logins_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginsFragment : Fragment() {

    companion object {
        private const val TAG = "LoginsFragment"
        fun newInstance() = LoginsFragment()
    }

    private val vm: LoginsViewModel by viewModel<LoginsViewModel>()

    private val sharepreference: SharedPreferenceUtils by inject<SharedPreferenceUtils>()

    private lateinit var arrayAdapter: ArrayAdapter<TypeLoginEnum>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.logins_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.initLiveDataLogin().observe(viewLifecycleOwner, Observer {
            if (it.isFailedFetch == true) {
                Toast.makeText(this.requireContext(), "gagal", Toast.LENGTH_SHORT).show()
            } else {
                if (it.iduser.equals("") && it.username.equals("") && it.TypeLogin == 0) {
                    Toast.makeText(this.requireContext(), "Failed Login ", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        this.requireContext(),
                        "Berhasil Login type= ${it.TypeLogin} + id ${it.iduser} + username ${it.username}",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.d(TAG, "putiduser: " + sharepreference.putIdUser(it.iduser))
                    sharepreference.putIdUser(it.iduser)
                    sharepreference.putNamaUser(it.nama)
                    sharepreference.putYearUser(it.year)

                    when (it.TypeLogin) {
                        TypeLoginEnum.TKI.jenis -> {
                            Log.d(TAG, "checkPassedUser: " + it.iduser)
                            vm.checkPassedUser(it.iduser)
                            if (it.status_document.equals("1") && it.status_pembayaran.equals("1")) {
                                val intent = Intent(activity, MainUserActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                activity?.startActivity(intent)
                            } else {
                                this.findNavController()
                                    .navigate(R.id.action_loginsFragment_to_nav_user)
                            }
                        }
                        TypeLoginEnum.PELATIH.jenis -> {
                            val intent = Intent(activity, MainPelatihActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            activity?.startActivity(intent)
                        }
                        TypeLoginEnum.PEGAWAI.jenis -> {
                            val intent = Intent(activity, MainHrdActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            activity?.startActivity(intent)
                        }
                    }
                }
            }
        })

        btn_register.setOnClickListener {
            this.findNavController().navigate(R.id.register_action)
        }

        btn_login.setOnClickListener {
            when (spr_categorylogin.selectedItemPosition) {
                TypeLoginEnum.PILIH_LOGIN.jenis -> {
                    Toast.makeText(
                        this.requireContext(),
                        "Pilih Jenis Login",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                TypeLoginEnum.TKI.jenis -> {
                    Log.d(TAG, "onActivityCreated: ${TypeLoginEnum.TKI.type}")

                    vm.loginUser(
                        edt_username.text.toString(),
                        edt_password.text.toString()
                    )
                }
                TypeLoginEnum.PELATIH.jenis -> {
                    Log.d(TAG, "onActivityCreated: ${TypeLoginEnum.PELATIH.type}")
                    vm.loginPelatih(edt_username.text.toString(), edt_password.text.toString())
                }
                TypeLoginEnum.PEGAWAI.jenis -> {
                    Log.d(TAG, "onActivityCreated: ${TypeLoginEnum.PEGAWAI.type}")
                    vm.loginPegawai(edt_username.text.toString(), edt_password.text.toString())
                }
                else -> { // Note the block
                    Log.w(TAG, "onItemClick: Not Have Object Selected On Enum")
                }
            }
        }

        arrayAdapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            TypeLoginEnum.values()
        )
        spr_categorylogin.dropDownVerticalOffset = 20
        spr_categorylogin.adapter = arrayAdapter

        img_whatsapp.setOnClickListener {
            val contact = "+62 81293480330"
            val url = "https://api.whatsapp.com/send?phone=$contact"
            try {
                val pm = this.requireContext().packageManager
                pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            } catch (e: PackageManager.NameNotFoundException) {
                Toast.makeText(
                    this.requireContext(),
                    "Whatsapp not Installed",
                    Toast.LENGTH_SHORT
                ).show()
                e.printStackTrace()
            }
        }
    }


}