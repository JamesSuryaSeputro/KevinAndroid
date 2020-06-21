package com.beone.kevin.ui.hrd

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beone.kevin.R

class CheckPembayaranFragment : Fragment() {

    companion object {
        fun newInstance() = CheckPembayaranFragment()
    }

    private lateinit var viewModel: CheckPembayaranViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.check_pembayaran_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CheckPembayaranViewModel::class.java)
        // TODO: Use the ViewModel
    }

}