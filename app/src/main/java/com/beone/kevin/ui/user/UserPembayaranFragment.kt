package com.beone.kevin.ui.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import kotlinx.android.synthetic.main.user_pembayaran_fragment.*
import org.koin.android.ext.android.inject

class UserPembayaranFragment : Fragment() {

    companion object {
        fun newInstance() = UserPembayaranFragment()
        private const val TAG = "UserPembayaranFragment"
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
            swp_refresh.isRefreshing = false

            if(!it.status.isNullOrEmpty()){
                if (it.status.equals("1")){
                    // TODO: 19/06/20 add navigation to nextfragment home user
                    view?.findNavController()?.navigate(R.id.action_userPembayaranFragment_to_uploadDocumentFragment)
                }else{
                    Log.d(TAG, "onActivityCreated: Not Been Check for Payment")
                }
            }

        })

        viewModel.checkPembayaran(sharedPreferenceUtils.getIdUser);

        swp_refresh.setOnRefreshListener {
            viewModel.checkPembayaran(sharedPreferenceUtils.getIdUser);
        }


        btn_pick_photo.setOnClickListener{
            // TODO: 19/06/20 add pick picture     
        }
        
        btn_upload.setOnClickListener{
            // TODO: 19/06/20 add Upload and message dialog before upload proof of payment
        }
        
    }

}