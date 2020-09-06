package com.beone.kevin.ui.user.profileuser

import android.graphics.Bitmap
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.beone.kevin.CustomImageUtils
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.ui.pelatih.profilecoach.ProfilePelatihViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.profile_pelatih_fragment.*
import kotlinx.android.synthetic.main.profile_pelatih_fragment.coach_name
import kotlinx.android.synthetic.main.profile_user_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileUserFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileUserFragment()
    }

    private val viewModel: ProfileUserViewModel by sharedViewModel()
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.initdata().observe(viewLifecycleOwner, Observer {
            alamat.text = it.alamat
            if(it.jeniskelamin.equals("1")){
                jenis_kelamin.text = getString(R.string.male)
            } else {
                jenis_kelamin.text = getString(R.string.female)
            }
            WN.text = it.kewarganegaraan
            tki_name.text = it.nama
            no_ktp.text = it.no_ktp
            no_passport.text = it.no_passport
            no_telp.text = it.notelp
            Glide.with(this)
                .load(CustomImageUtils.stringToBitmap(it.passfoto))
                .centerCrop()
                .error(android.R.color.background_dark)
                .into(img_pasfoto)
            tanggal_lahir.text = it.tanggallahir
            tempat_lahir.text = it.tempatlahir
            tki_username.text = it.username
            active_since.text = it.year
        })

        viewModel.getProfile(sharedPreferenceUtils.getIdUser)
    }
}