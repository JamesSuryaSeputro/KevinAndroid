package com.beone.kevin.ui.hrd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.beone.kevin.R
import kotlinx.android.synthetic.main.logins_fragment.*
import kotlinx.android.synthetic.main.menu_hrd_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuHrdFragment : Fragment() {

    companion object {
        fun newInstance() = MenuHrdFragment()
    }

    private val viewModel: MenuHrdViewModel by viewModel<MenuHrdViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_hrd_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        btn_tambah_pelatih.setOnClickListener {
            this.findNavController()?.navigate(R.id.action_menuHrdFragment_to_registerCoachFragment)
        }
        btn_tambah_hrd.setOnClickListener {
            this.findNavController()?.navigate(R.id.action_menuHrdFragment_to_registerEmployeeFragment)
        }
        btn_logout.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_global_loginsFragment)
        }
    }



}