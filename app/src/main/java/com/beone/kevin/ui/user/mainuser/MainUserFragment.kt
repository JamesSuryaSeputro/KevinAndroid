package com.beone.kevin.ui.user.mainuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.beone.kevin.R
import kotlinx.android.synthetic.main.main_user_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainUserFragment : Fragment() {

    companion object {
        fun newInstance() = MainUserFragment()
    }

    private val viewModel: MainUserViewModel by viewModel<MainUserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_profile.setOnClickListener {

            val action = MainUserFragmentDirections.actionMainUserFragmentToProfilTki(true)
            this.findNavController().navigate(action)
        }
    }
}