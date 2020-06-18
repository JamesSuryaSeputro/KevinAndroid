package com.beone.kevin.ui.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beone.kevin.R

class LoginsFragment : Fragment() {

    companion object {
        fun newInstance() = LoginsFragment()
    }

    private lateinit var viewModel: LoginsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.logins_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginsViewModel::class.java)
        // TODO: Use the ViewModel

    }

}