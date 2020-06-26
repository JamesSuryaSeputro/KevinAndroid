package com.beone.kevin.ui.pelatih.MainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.beone.kevin.R
import kotlinx.android.synthetic.main.main_menu_pelatih_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainMenuPelatihFragment : Fragment() {

    companion object {
        fun newInstance() =
            MainMenuPelatihFragment()
    }

    private val viewModel: MainMenuPelatihViewModel by viewModel<MainMenuPelatihViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_menu_pelatih_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_tambah_jadwal.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainMenuPelatihFragment_to_schedulePelatihFragment)
        }

        btn_tambah_nilai.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainMenuPelatihFragment_to_addNilaiFragment)
        }


    }
}