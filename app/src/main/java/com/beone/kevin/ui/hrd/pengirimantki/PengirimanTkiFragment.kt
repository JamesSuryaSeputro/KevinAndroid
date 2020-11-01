package com.beone.kevin.ui.hrd.pengirimantki

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.beone.kevin.R
import kotlinx.android.synthetic.main.pengiriman_tki_fragment.*
import org.koin.android.ext.android.inject

class PengirimanTkiFragment : Fragment(), OnClickSend {

    companion object {
        fun newInstance() = PengirimanTkiFragment()
        const val TAG = "PengirimanTkiFragment"
    }

    private val viewModel: PengirimanTkiViewModel by inject()
    private val pengirimanTkiAdapter: PengirimanTkiAdapter = PengirimanTkiAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pengiriman_tki_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rcv_pengirimantki.adapter = pengirimanTkiAdapter

        viewModel.initData().observe(viewLifecycleOwner, Observer {
            pengirimanTkiAdapter.swapData(it)
        })

        viewModel.getDataPengirimanTki()
    }

    override fun onClick(id: String?) {
        Toast.makeText(this.requireContext(), "TKI siap dikirim", Toast.LENGTH_SHORT).show()
    }

}