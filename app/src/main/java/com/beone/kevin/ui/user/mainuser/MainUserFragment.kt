package com.beone.kevin.ui.user.mainuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import kotlinx.android.synthetic.main.main_user_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainUserFragment : Fragment() {

    companion object {
        fun newInstance() = MainUserFragment()
    }

    private val viewModel: MainUserViewModel by viewModel<MainUserViewModel>()

    private val sharepreference: SharedPreferenceUtils by inject<SharedPreferenceUtils>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        id_user.text = sharepreference.getIdUser

        btn_profile.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainUserFragment_to_profileUserFragment)
        }
        btn_logout.setOnClickListener{
            it.findNavController().navigate(R.id.action_global_loginsFragment)
        }
    }
}