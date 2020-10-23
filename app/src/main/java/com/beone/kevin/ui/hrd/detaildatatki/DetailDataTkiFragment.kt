package com.beone.kevin.ui.hrd.detaildatatki

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.beone.kevin.CustomImageUtils
import com.beone.kevin.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_data_tki_fragment.*
import kotlinx.android.synthetic.main.profile_user_fragment.view.*
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class DetailDataTkiFragment : Fragment() {

    companion object {
        fun newInstance() = DetailDataTkiFragment()
    }

    private val viewModel: DetailDataTkiViewModel by inject()
    private lateinit var idUser: String
    val args: DetailDataTkiFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_data_tki_fragment, container, false)
    }

    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        idUser = args.iduser

        viewModel.initData().observe(viewLifecycleOwner, Observer {
            if (!"".equals(it.passfoto)) {
                Glide.with(this)
                    .load(CustomImageUtils.stringToBitmap(it.passfoto))
                    .fitCenter()
                    .error(android.R.color.background_dark)
                    .into(iv_dataphoto)
            }

            tv_dataname.text = it.nama
            when (it.id_user.length) {
                1 -> {
                    tv_dataiduser.text =
                        "ID User: 21" + it.year + "00" + it.id_user
                }
                2 -> {
                    tv_dataiduser.text =
                        "ID User: 21" + it.year + "0" + it.id_user
                }
                else -> {
                    tv_dataiduser.text = "ID User: 21" + it.year + it.id_user
                }
            }
            tv_dataactivesince.text = "Active since: " + it.year
            tv_datausername.text = "Username: " + it.username
            tv_datapassport.text = "No. passport: " + it.no_passport
            tv_dataktp.text = "No. KTP: " + it.no_ktp
            tv_datatempatlahir.text = "Tempat lahir: " + it.tempatlahir
            tv_datatanggallahir.text = "Tanggal lahir: " + it.tanggallahir
            tv_datawn.text = "Kewarganegaraan: " + it.kewarganegaraan
            tv_datagender.text = "Gender: " + it.jeniskelamin
            tv_dataalamat.text = "Alamat: " + it.alamat
            tv_datatelp.text = "No. telepon: " + it.notelp

            if (!"".equals(it.scan_ktp)) {
                Glide.with(this)
                    .load(CustomImageUtils.stringToBitmap(it.scan_ktp))
                    .fitCenter()
                    .error(android.R.color.background_dark)
                    .into(iv_dataktp)
            }

            if (!"".equals(it.scan_kompensasi)) {
                Glide.with(this)
                    .load(CustomImageUtils.stringToBitmap(it.scan_kompensasi))
                    .fitCenter()
                    .error(android.R.color.background_dark)
                    .into(iv_datakompensasi)
            }

            if (!"".equals(it.scan_surat_kesehatan)) {
                Glide.with(this)
                    .load(CustomImageUtils.stringToBitmap(it.scan_surat_kesehatan))
                    .fitCenter()
                    .error(android.R.color.background_dark)
                    .into(iv_datasuratkesehatan)
            }

            if (!"".equals(it.scan_surat_kerja)) {
                Glide.with(this)
                    .load(CustomImageUtils.stringToBitmap(it.scan_surat_kerja))
                    .fitCenter()
                    .error(android.R.color.background_dark)
                    .into(iv_datasuratkerja)
            }
        })

        viewModel.getData(idUser)
    }
}