package com.beone.kevin.ui.user.uploaddocument

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.beone.kevin.CustomImageUtils
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import kotlinx.android.synthetic.main.upload_document_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UploadDocumentFragment : Fragment() {

    companion object {
        fun newInstance() =
            UploadDocumentFragment()

        private const val TAG = "UploadDocumentFragment"
        const val RESULT_KTP = 12
        const val RESULT_KOMPENSASI = 13
        const val RESULT_KESEHATAN = 14
        const val RESULT_KERJA = 15
    }

    private val viewModel: UploadDocumentViewModel by viewModel<UploadDocumentViewModel>()
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject<SharedPreferenceUtils>()
    private var bitmapKtp: Bitmap? = null
    private var bitmapKompensasi: Bitmap? = null
    private var bitmapKesehatan: Bitmap? = null
    private var bitmapKerja: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.upload_document_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.initLiveData().observe(viewLifecycleOwner, Observer {
            if (!"".equals(it.scanktp)){
                tv_location_ktp.text = "ada"
            }
            if (!"".equals(it.scansuratkesehatan)){
                tv_location_kesehatan.text = "ada"
            }
            if (!"".equals(it.scankompensasi)){
                tv_location_kompensasi.text = "ada"
            }
            if (!"".equals(it.scansuratkerja)){
                tv_location_suratkerja.text = "ada"
            }

            if (it.status.equals("0")){

                tv_status.text =  "Status : Pending"
            }else{
                tv_status.text =  "Status : Success"
                view?.findNavController()?.navigate(R.id.action_uploadDocumentFragment_to_userPembayaranFragment)
            }

        })

        viewModel.getData(sharedPreferenceUtils.getIdUser)

        btn_uploadKtp.setOnClickListener {
            openGallery(RESULT_KTP)
        }
        btn_uploadKesehatan.setOnClickListener {
            openGallery(RESULT_KESEHATAN)
        }
        btn_uploadKompensasi.setOnClickListener {
            openGallery(RESULT_KOMPENSASI)
        }
        btn_uploadSuratKerja.setOnClickListener {
            openGallery(RESULT_KERJA)
        }

        btn_uploadall.setOnClickListener {
            viewModel.uploadDocUser(
                sharedPreferenceUtils.getIdUser,

                bitmapKtp?.let { it1 -> CustomImageUtils.BitmapToString(it1) },
                bitmapKompensasi?.let { it1 -> CustomImageUtils.BitmapToString(it1) },
                bitmapKesehatan?.let { it1 -> CustomImageUtils.BitmapToString(it1) },
                bitmapKerja?.let { it1 -> CustomImageUtils.BitmapToString(it1) }

            )
        }
    }

    fun openGallery(requestCode: Int) {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult: ${requestCode} ${resultCode} ${data?.data}")
        if (data != null) {
            when (requestCode) {
                RESULT_KTP -> {
                    if (data?.data != null) {
                        bitmapKtp = MediaStore.Images.Media.getBitmap(
                            this.requireContext().contentResolver,
                            data.data
                        )
                        tv_location_ktp.text = data.data.toString()
                    }
                }
                RESULT_KOMPENSASI -> {
                    if (data?.data != null) {
                        bitmapKompensasi = MediaStore.Images.Media.getBitmap(
                            this.requireContext().contentResolver,
                            data.data
                        )
                        tv_location_kompensasi.text = data.data.toString()
                    }
                }
                RESULT_KESEHATAN -> {
                    if (data?.data != null) {
                        bitmapKesehatan = MediaStore.Images.Media.getBitmap(
                            this.requireContext().contentResolver,
                            data.data
                        )
                        tv_location_kesehatan.text = data.data.toString()
                    }
                }
                RESULT_KERJA -> {
                    if (data?.data != null) {
                        bitmapKerja = MediaStore.Images.Media.getBitmap(
                            this.requireContext().contentResolver,
                            data.data
                        )
                        tv_location_suratkerja.text = data.data.toString()
                    }
                }
            }
        }
    }
}