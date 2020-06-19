package com.beone.kevin.ui.user

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import kotlinx.android.synthetic.main.user_pembayaran_fragment.*
import org.koin.android.ext.android.inject

class UserPembayaranFragment : Fragment() {

    companion object {
        fun newInstance() = UserPembayaranFragment()
    }

    private val viewModel: UserPembayaranViewModel by inject()
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_pembayaran_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.initDataPembayaran().observe(viewLifecycleOwner, Observer {
            tv_nama.text = it.nama;

            if(it.status!=null){
                if (it.status.equals("1")){
                    // TODO: 19/06/20 add navigation to nextfragment home user
                }
            }

        })

        viewModel.checkPembayaran(sharedPreferenceUtils.getIdUser);

        btn_pick_photo.setOnClickListener{
            // TODO: 19/06/20 add pick picture     
        }
        
        btn_upload.setOnClickListener{
            // TODO: 19/06/20 add Upload and message dialog before upload proof of payment
        }
        
    }

}