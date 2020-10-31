package com.beone.kevin.ui.hrd.datatki

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beone.kevin.R
import kotlinx.android.synthetic.main.data_tki_fragment.*
import org.koin.android.ext.android.inject

class DataTkiFragment : Fragment(), OnClickListData {

    companion object {
        fun newInstance() = DataTkiFragment()
        private const val TAG = "DataTkiFragment"
    }

    private val viewModel: DataTkiViewModel by inject()
    private val dataTkiAdapter: DataTkiAdapter = DataTkiAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.data_tki_fragment, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rcv_listdatatki.adapter = dataTkiAdapter

        mainSearchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchUser(query)
                mainSearchText.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, newText.toString())
                if (newText!!.isEmpty() || newText == "") {
                    viewModel.showUser()
                }
                return true
            }
        })

        viewModel.initData2().observe(viewLifecycleOwner, Observer {
            dataTkiAdapter.searchData(it)
        })

        viewModel.initData().observe(viewLifecycleOwner, Observer {
            dataTkiAdapter.swapData(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it!= null) {
                if (it) {
                    progressbar.visibility = View.GONE
                }
            }
        })

        viewModel.showUser()
    }

    override fun onClick(id: String?) {
        val action =
            DataTkiFragmentDirections.actionNavigationDatatkiToDetailDataTkiFragment(id.toString())
        this.findNavController().navigate(action)
    }
}