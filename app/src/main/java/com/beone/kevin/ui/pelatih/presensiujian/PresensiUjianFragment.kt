package com.beone.kevin.ui.pelatih.presensiujian

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.beone.kevin.R
import com.beone.kevin.ui.pelatih.selecttkifortraining.SelectTkiForTrainingViewModel
import kotlinx.android.synthetic.main.presensi_ujian_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PresensiUjianFragment : Fragment(), PresensiUjianAdapter.PresensiUjianListener,
    AdapterView.OnItemSelectedListener,
    AdapterView.OnItemClickListener {

    companion object {
        fun newInstance() = PresensiUjianFragment()
        private const val TAG = "PresensiUjianFragment"
    }

    //    private lateinit var arrayData: ArrayAdapter<JadwalPelatihModelItem>
    private val adapter: PresensiUjianAdapter = PresensiUjianAdapter(this)

    //  private val sharedPreferenceUtils:SharedPreferenceUtils by inject<SharedPreferenceUtils>()
    private val viewModel: PresensiUjianViewModel by viewModel<PresensiUjianViewModel>()
    private lateinit var idJadwalDetail: String
    private lateinit var idJadwal: String
    private lateinit var hari: String
    private lateinit var tanggal: String
    private lateinit var jamMulai: String
    private lateinit var jamSelesai: String
    val args: PresensiUjianFragmentArgs by navArgs<PresensiUjianFragmentArgs>()
   // private val viewModel2: SelectTkiForTrainingViewModel by sharedViewModel<SelectTkiForTrainingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.presensi_ujian_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idJadwalDetail = args.idJadwalDetail
        idJadwal = args.idJadwal
        hari = args.hari
        tanggal = args.tanggal
        jamMulai = args.jammulai
        jamSelesai = args.jamselesai
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel.initSpinner().observe(viewLifecycleOwner, Observer {
//            arrayData.addAll(it)
//        })
//        viewModel.initRecycler().observe(viewLifecycleOwner, Observer {
//            adapter.swapData(it)
//        })
//        viewModel.initPresensi().observe(viewLifecycleOwner, Observer {
//            if (it.status.equals(1)) {
//
//                Toast.makeText(this.requireContext(), "ok", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        })
        viewModel.initData().observe(viewLifecycleOwner, Observer {
            adapter.swapData(it)
        })

        viewModel.getDataFragmentPresensi(idJadwal, idJadwalDetail)
        //viewModel.addPresensiUjian(idJadwalDetail, sharedPreferenceUtils.getIdUser, "1")
        //viewModel2.getDataStatus(idJadwal)

        viewModel.initPresensi().observe(viewLifecycleOwner, Observer {
            if (it.status.equals(1)) {

                Toast.makeText(this.requireContext(), "Ok", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this.requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        })

//        viewModel.initCheck().observe(viewLifecycleOwner, Observer {
//            if (it.status.equals(0)) {
//
//                Toast.makeText(this.requireContext(), "insert", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                Toast.makeText(this.requireContext(), "update", Toast.LENGTH_SHORT).show()
//            }
//        })

//        viewModel.idPresensi.observe(viewLifecycleOwner, Observer {
//            if (it!= null) {
//                idPresensi = it
//            }
//        })

//        viewModel.addPresensiUjian(idJadwalDetail, sharedPreferenceUtils.getIdUser, "1")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        arrayData =
//            ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_dropdown_item)
//        spnr_jadwal.adapter = arrayData

        tv_tgldetailpresensi.text = hari + ", " + tanggal + " (" + jamMulai + "-" + jamSelesai + ")"

        rcv_presensiujian.adapter = adapter

//        viewModel.getdatajadwal(sharedPreferenceUtils.getIdUser)

//        spnr_jadwal.onItemSelectedListener = this
    }

    override fun deleteUser(position: Int, iduser: String?) {
//        adapter.removeData(position)
        //api add presensi
        Log.d(TAG, "deleteUser: ${position}")
//        viewModel.addPresensiUjian(arrayData.getItem(spnr_jadwal.selectedItemPosition)?.id_jadwal,iduser)
    }

    override fun clearStatus(idpelatihan: String?, iduser: String?) {
        viewModel.checkPresensi(idJadwalDetail, iduser, "0")
       // viewModel.addPresensiUjian(idJadwalDetail, iduser, "0", idpelatihan)
    }

    override fun checkStatus(idpelatihan: String?, iduser: String?) {
        viewModel.checkPresensi(idJadwalDetail, iduser, "1")
        //viewModel.addPresensiUjian(idJadwalDetail, iduser, "1", idpelatihan)
       // Toast.makeText(this.requireContext(), "not insert, query update", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        viewModel.getUser(arrayData.getItem(p2)?.id_jadwal)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

}