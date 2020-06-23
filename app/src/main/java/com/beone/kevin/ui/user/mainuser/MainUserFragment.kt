package com.beone.kevin.ui.user.mainuser

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beone.kevin.R

class MainUserFragment : Fragment() {

    companion object {
        fun newInstance() = MainUserFragment()
    }

    private lateinit var viewModel: MainUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}