package com.beone.kevin.ui.pelatih.selecttkifortraining

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.addselecttkifortrainingdialogfragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddSelectTkiForTrainingDialogFragment : BottomSheetDialogFragment(),
    SelectTkiAdapter.OnSelectTkiListener {

    private val viewModel: SelectTkiForTrainingViewModel by sharedViewModel<SelectTkiForTrainingViewModel>()
    private val adapter: SelectTkiAdapter = SelectTkiAdapter(this)
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject<SharedPreferenceUtils>()
    private lateinit var idPilihan: String
    val args: AddSelectTkiForTrainingDialogFragmentArgs by navArgs<AddSelectTkiForTrainingDialogFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idPilihan = args.idPilihan
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.addselecttkifortrainingdialogfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcv_select_tki.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.initDialog().observe(viewLifecycleOwner, Observer {
            adapter.swapData(it)
        })

        viewModel.initStatusUpload().observe(viewLifecycleOwner, Observer {
            if (it.status.equals(1)) {
                dismiss()
            }
        })

        viewModel.getDataDialog(idPilihan)
    }

    override fun onAddTki(idUser: String?) {
        viewModel.addUserToJadwal(idUser, idPilihan)
    }

    override fun onDeleteTki(idUser: String?) {

    }
}