package com.beone.kevin.ui.pelatih.MainMenu

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import kotlinx.android.synthetic.main.main_menu_pelatih_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainMenuPelatihFragment : Fragment() {

    companion object {
        fun newInstance() =
            MainMenuPelatihFragment()
    }

    private val viewModel: MainMenuPelatihViewModel by viewModel<MainMenuPelatihViewModel>()

    private val sharepreference: SharedPreferenceUtils by inject<SharedPreferenceUtils>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_menu_pelatih_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(ContentValues.TAG, "getiduser: " + sharepreference.getIdUser)
        id_pelatih.text = sharepreference.getIdUser

        btn_tambah_jadwal.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainMenuPelatihFragment_to_schedulePelatihFragment)
        }

        btn_tambah_nilai.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainMenuPelatihFragment_to_addNilaiFragment)
        }

        btn_logout.setOnClickListener {
            Log.d(ContentValues.TAG, "btn_logout: " + sharepreference.removeUser())
            sharepreference.removeUser()
            it.findNavController().navigate(R.id.action_global_loginsFragment)
        }

        btn_presensi_ujian.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainMenuPelatihFragment_to_presensiUjianFragment)
        }
        btn_presensi_test.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainMenuPelatihFragment_to_presensi_fragment)
        }
        btn_Profile.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainMenuPelatihFragment_to_profilePelatihFragment)
        }
    }
}