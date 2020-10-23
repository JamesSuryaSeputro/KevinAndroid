package com.beone.kevin.ui.pelatih.addnilai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.remote.model.JadwalPelatihModelItem
import kotlinx.android.synthetic.main.add_nilai_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNilaiFragment : Fragment(), AdapterView.OnItemSelectedListener,
    NilaiAdapter.OnSelectTkiListener {

    companion object {
        fun newInstance() = AddNilaiFragment()
        private const val TAG = "AddNilaiFragment"
    }

    private val viewModel: AddNilaiViewModel by viewModel<AddNilaiViewModel>()
    private lateinit var arrayDataSpiner: ArrayAdapter<JadwalPelatihModelItem>
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject<SharedPreferenceUtils>()
    private val nilaiAdapter: NilaiAdapter = NilaiAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_nilai_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.initFragment().observe(viewLifecycleOwner, Observer {
            arrayDataSpiner.addAll(it)
        })

        viewModel.initFragment2().observe(viewLifecycleOwner, Observer {
            nilaiAdapter.swapData(it)
        })

        viewModel.getDataJadwal(sharedPreferenceUtils.getIdUser)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayDataSpiner =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item)
//        spnr_jadwal.adapter = arrayDataSpiner
//        spnr_jadwal.onItemSelectedListener = this
        rcv_nilai.adapter = nilaiAdapter
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        viewModel.getDataSiswa(arrayDataSpiner.getItem(p2)?.id_jadwal)

    }

    override fun onAddTki(idUser: String?, nilai: String?) {
//        viewModel.addNilaiSiswa(
//            idUser,
//            nilai,
//            arrayDataSpiner.getItem(spnr_jadwal.selectedItemPosition)?.id_jadwal
//        )
    }
}