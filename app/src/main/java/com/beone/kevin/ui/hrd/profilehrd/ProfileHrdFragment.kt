package com.beone.kevin.ui.hrd.profilehrd

import android.content.Intent
import android.graphics.Bitmap
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.beone.kevin.CustomImageUtils
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.profile_hrd_fragment.*
import kotlinx.android.synthetic.main.profile_pelatih_fragment.*
import kotlinx.android.synthetic.main.profile_pelatih_fragment.coach_name
import kotlinx.android.synthetic.main.profile_pelatih_fragment.coach_username
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileHrdFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileHrdFragment()
        private const val RESULT_FOTO = 12
    }

    private val viewModel: ProfileHrdViewModel by sharedViewModel()
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject()
    private var bitmap: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_hrd_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.initdata().observe(viewLifecycleOwner, Observer {
            employee_name.text = it.nama_pegawai
            employee_username.text = it.username
            employee_nip.text = it.nip
            Glide.with(this)
                .load(CustomImageUtils.stringToBitmap(it.foto))
                .error(android.R.color.background_dark)
                .into(img_view_employeeprofile)
        })

        viewModel.initdata2().observe(viewLifecycleOwner, Observer {
            if (it.status.equals(1)) {

                Toast.makeText(this.requireContext(), "Berhasil ubah foto", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        btn_pick_employeeimg.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, RESULT_FOTO)
        }

        viewModel.getprofile(sharedPreferenceUtils.getIdUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RESULT_FOTO -> {
                bitmap =
                    MediaStore.Images.Media.getBitmap(this.context?.contentResolver, data?.data)
                if (bitmap != null) {
                    var foto: String? = ""
                    foto = bitmap?.let { it1 -> CustomImageUtils.BitmapToString(it1) }
                    viewModel.updatefoto(sharedPreferenceUtils.getIdUser, foto)
                    if(fragmentManager != null){
                        fragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
                    }
                }
            }
        }
    }
}