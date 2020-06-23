package com.beone.kevin.ui.user.mainuser

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beone.kevin.R

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
        // TODO: Use the ViewModel
    }

}