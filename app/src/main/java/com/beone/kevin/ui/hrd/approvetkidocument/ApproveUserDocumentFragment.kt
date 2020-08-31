package com.beone.kevin.ui.hrd.approvetkidocument

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beone.kevin.CustomImageUtils
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.approve_user_document_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ApproveUserDocumentFragment : Fragment() {

    companion object {
        fun newInstance() = ApproveUserDocumentFragment()
        private const val TAG = "ApproveUserDocumentFragment"
    }

    private val viewModel: ApproveUserDocumentViewModel by sharedViewModel()
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject()
    private lateinit var iduser: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.approve_user_document_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        iduser = arguments?.getString("id").toString()
        val nama = arguments?.getString("nama")

        Log.d(TAG, "nama: " + nama)
        tv_namauser.text = nama

        viewModel.initDataUserDocument().observe(viewLifecycleOwner, Observer {

            Glide.with(this)
                .load(CustomImageUtils.stringToBitmap(it.scanktp))
                .error(android.R.color.background_dark)
                .into(img_ktp)

            Glide.with(this)
                .load(CustomImageUtils.stringToBitmap(it.scankompensasi))
                .error(android.R.color.background_dark)
                .into(img_kompensasi)

            Glide.with(this)
                .load(CustomImageUtils.stringToBitmap(it.scansuratkesehatan))
                .error(android.R.color.background_dark)
                .into(img_suratkesehatan)

            Glide.with(this)
                .load(CustomImageUtils.stringToBitmap(it.scansuratkerja))
                .error(android.R.color.background_dark)
                .into(img_suratkerja)
        })

        viewModel.initDataStatus().observe(viewLifecycleOwner, Observer {
            if (it.status.equals("1")) {

                Toast.makeText(this.requireContext(), "Dokumen Disetujui", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        Log.d(TAG, "iduser: " + iduser)
        viewModel.showDocument(iduser)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_approve.setOnClickListener {
            viewModel.approveDocument(iduser, sharedPreferenceUtils.getIdUser, "1")
            this.findNavController().popBackStack()
        }

        btn_decline.setOnClickListener {
            this.findNavController().popBackStack()
        }
    }
}