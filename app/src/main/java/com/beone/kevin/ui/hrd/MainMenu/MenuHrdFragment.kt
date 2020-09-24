package com.beone.kevin.ui.hrd.MainMenu

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import kotlinx.android.synthetic.main.menu_hrd_fragment.*
import kotlinx.android.synthetic.main.menu_hrd_fragment.btn_logout
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MenuHrdFragment : Fragment() {

    companion object {
        fun newInstance() = MenuHrdFragment()
    }

    private val viewModel: MenuHrdViewModel by viewModel<MenuHrdViewModel>()

    private val sharepreference: SharedPreferenceUtils by inject<SharedPreferenceUtils>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_hrd_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(ContentValues.TAG, "getiduser: " + sharepreference.getIdUser)
        id_pegawai.text = sharepreference.getIdUser

        btn_tambah_pelatih.setOnClickListener {
            this.findNavController().navigate(R.id.action_menuHrdFragment_to_registerCoachFragment)
        }
        btn_tambah_hrd.setOnClickListener {
            this.findNavController().navigate(R.id.action_menuHrdFragment_to_registerEmployeeFragment)
        }
        btn_memeriksa_dokumen.setOnClickListener {
            this.findNavController().navigate(R.id.action_menuHrdFragment_to_checkDocumentFragment)
        }
        btn_memeriksa_pembayaran.setOnClickListener {
            this.findNavController().navigate(R.id.action_menuHrdFragment_to_checkPembayaranFragment)
        }
        btn_profile.setOnClickListener {
            this.findNavController().navigate(R.id.action_menuHrdFragment_to_profileHrdFragment)
        }
        btn_logout.setOnClickListener{
            Log.d(ContentValues.TAG, "btn_logout: " + sharepreference.removeUser())
            sharepreference.removeUser()
            view?.findNavController()?.navigate(R.id.action_global_loginsFragment)
        }
    }
}