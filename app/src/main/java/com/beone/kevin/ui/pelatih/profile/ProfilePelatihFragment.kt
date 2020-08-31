package com.beone.kevin.ui.pelatih.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.profile_pelatih_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfilePelatihFragment : Fragment() {

    companion object {
        fun newInstance() = ProfilePelatihFragment()
    }

    private val viewModel: ProfilePelatihViewModel by viewModel<ProfilePelatihViewModel>()
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject<SharedPreferenceUtils>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_pelatih_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.initdata().observe(viewLifecycleOwner, Observer {
            tv_nama.text = "Nama : "+it.nama_pelatih
            tv_username.text = "Username : "+it.username
            tv_active_since.text = "Active since :" + it.datecreated
        })
        viewModel.getprofile(sharedPreferenceUtils.getIdUser)
    }


}