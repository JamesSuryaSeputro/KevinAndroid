package com.beone.kevin.ui.pelatih.selecttkifortraining

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.beone.kevin.R
import kotlinx.android.synthetic.main.select_tki_for_training_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SelectTkiForTrainingFragment : Fragment(),
    SelectTkiAdapter.OnSelectTkiListener {

    companion object {
        fun newInstance() = SelectTkiForTrainingFragment()
        private const val TAG = "SelectTkiForTrainingFra"
    }

    private val viewModel: SelectTkiForTrainingViewModel by sharedViewModel<SelectTkiForTrainingViewModel>()
    private lateinit var idPilihan: String

    private val adapter: SelectTkiAdapter = SelectTkiAdapter(this)

    val args: SelectTkiForTrainingFragmentArgs by navArgs<SelectTkiForTrainingFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idPilihan = args.idpilihan
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.select_tki_for_training_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.initFragment().observe(viewLifecycleOwner, Observer {
            adapter.swapData(it)
        })

        viewModel.initStatuUpload().observe(viewLifecycleOwner, Observer {
            if (it.status.equals(1)) {
                viewModel.getDataFragemnt(idPilihan)
            }
        })
        viewModel.getDataFragemnt(idPilihan)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcv_select_tki_fragment.adapter = adapter
        btn_upload.setOnClickListener {
            val action =
                SelectTkiForTrainingFragmentDirections.actionSelectTkiForTrainingFragmentToAddSelectTkiForTrainingDialogFragment(
                    idPilihan
                )
            view.findNavController().navigate(action)
        }
    }

    override fun onAddTki(idUser: String?) {
        //viewModel.addUserToJadwal(idjadwal,idUser);
    }

    override fun onDeleteTki(idUser: String?) {
        viewModel.deleteUserToJadwal(idUser);
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}