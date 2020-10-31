package com.beone.kevin.ui.pelatih.addnilai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import kotlinx.android.synthetic.main.add_nilai_fragment.*
import kotlinx.android.synthetic.main.presensi_ujian_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNilaiFragment : Fragment(), AdapterView.OnItemSelectedListener, OnSelectTkiListener {

    companion object {
        fun newInstance() = AddNilaiFragment()
        private const val TAG = "AddNilaiFragment"
    }

    private val viewModel: AddNilaiViewModel by viewModel<AddNilaiViewModel>()

    //private lateinit var arrayDataSpiner: ArrayAdapter<JadwalPelatihModelItem>
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject<SharedPreferenceUtils>()
    private lateinit var idJadwal: String
    private lateinit var idJadwalDetail: String
    private lateinit var hari: String
    private lateinit var tanggal: String
    private lateinit var jamMulai: String
    private lateinit var jamSelesai: String
    val args: AddNilaiFragmentArgs by navArgs<AddNilaiFragmentArgs>()
    private val nilaiAdapter: NilaiAdapter = NilaiAdapter(this)
    // private val viewModel2: SelectTkiForTrainingViewModel by sharedViewModel<SelectTkiForTrainingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_nilai_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idJadwal = args.idJadwal
        idJadwalDetail = args.idJadwalDetail
        hari = args.hari
        tanggal = args.tanggal
        jamMulai = args.jammulai
        jamSelesai = args.jamselesai
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel.initFragment().observe(viewLifecycleOwner, Observer {
//            arrayDataSpiner.addAll(it)
//        })

//        viewModel.initFragment2().observe(viewLifecycleOwner, Observer {
//            nilaiAdapter.swapData(it)
//        })

        viewModel.initNilai().observe(viewLifecycleOwner, Observer {
            nilaiAdapter.swapData(it)
        })

        viewModel.initStatus().observe(viewLifecycleOwner, Observer {
            if (it.status.equals(1)) {

                Toast.makeText(this.requireContext(), "Berhasil submit", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this.requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.getDataNilaiSiswa(idJadwal, idJadwalDetail)
//        viewModel.getDataJadwal(sharedPreferenceUtils.getIdUser)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        arrayDataSpiner =
//            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item)
//        spnr_jadwal.adapter = arrayDataSpiner
        //   spnr_jadwal.onItemSelectedListener = this

        tv_tgldetailnilai.text = hari + ", " + tanggal + " (" + jamMulai + "-" + jamSelesai + ")"

        rcv_nilai.adapter = nilaiAdapter
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //  viewModel.getDataSiswa(arrayDataSpiner.getItem(p2)?.id_jadwal)

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onAddTki(idpelatihan: String?, nilai: String?) {
        viewModel.checkNilai(
            idpelatihan,
            idJadwalDetail,
            nilai
        )
    }
}