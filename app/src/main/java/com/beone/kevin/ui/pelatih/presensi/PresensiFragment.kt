//package com.beone.kevin.ui.pelatih.presensi
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import com.beone.kevin.R
//import com.beone.kevin.SharedPreferenceUtils
//import com.beone.kevin.remote.model.JadwalPelatihModelItem
//import com.beone.kevin.ui.pelatih.presensiujian.PresensiUjianAdapter
//import kotlinx.android.synthetic.main.presensi_fragment.*
//import org.koin.android.ext.android.inject
//import org.koin.androidx.viewmodel.ext.android.viewModel
//
//class PresensiFragment : Fragment(),PresensiUjianAdapter.PresensiUjianListener,AdapterView.OnItemSelectedListener {
//
//    companion object {
//        fun newInstance() = PresensiFragment()
//    }
//
//    private lateinit var arrayData: ArrayAdapter<JadwalPelatihModelItem>
//    private val viewModel: PresensiViewModel by viewModel<PresensiViewModel>()
//    private val sharedPreferenceUtils:SharedPreferenceUtils by inject<SharedPreferenceUtils>()
//    private val adapter:PresensiUjianAdapter = PresensiUjianAdapter(this)
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.presensi_fragment, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel.initSpinner().observe(viewLifecycleOwner, Observer {
//            arrayData.addAll(it)
//        })
//        viewModel.initRecycler().observe(viewLifecycleOwner, Observer {
//            adapter.swapData(it)
//        })
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        arrayData =
//            ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_dropdown_item)
//        spnr_jadwal.adapter = arrayData
//
//        rcv_presensiujian.adapter = adapter
//
//        viewModel.getdatajadwal(sharedPreferenceUtils.getIdUser)
//
//        spnr_jadwal.onItemSelectedListener = this
//    }
//
//    override fun deleteUser(position: Int, iduser: String?) {
//        adapter.removeData(position)
//        //api add presensi
//        viewModel.addPresensiTest(arrayData.getItem(spnr_jadwal.selectedItemPosition)?.id_jadwal,iduser)
//    }
//
//    override fun onNothingSelected(p0: AdapterView<*>?) {
//    }
//
//    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        viewModel.getUser(arrayData.getItem(p2)?.id_jadwal)
//    }
//}