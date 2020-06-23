package com.beone.kevin.ui.user.userpembayaran

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
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_pembayaran_fragment.*
import org.koin.android.ext.android.inject

class UserPembayaranFragment : Fragment() {

    companion object {
        fun newInstance() =
            UserPembayaranFragment()
        private const val TAG = "UserPembayaranFragment"
        private const val RESULT_GALLERY = 12;
    }

    private val viewModel: UserPembayaranViewModel by inject()
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject()
    private lateinit var bitmap: Bitmap


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

            if (!it.status.isNullOrEmpty()) {
                if (it.status.equals("1")) {
                    // TODO: 19/06/20 add navigation to nextfragment home user
                    view?.findNavController()
                        ?.navigate(R.id.action_userPembayaranFragment_to_uploadDocumentFragment)
                } else {
                    Log.d(TAG, "onActivityCreated: Not Been Check for Payment")
                }
            }

            if (!it.img_bukti.equals("")) {
                Glide.with(this)
                    .load(CustomImageUtils.stringToBitmap(it.img_bukti))
                    .error(android.R.color.background_dark)
                    .into(img_bukti_pembayaran)
            }

        })

        viewModel.checkPembayaran(sharedPreferenceUtils.getIdUser);

        swp_refresh.setOnRefreshListener {
            viewModel.checkPembayaran(sharedPreferenceUtils.getIdUser);
        }


        btn_pick_photo.setOnClickListener {
            // TODO: 19/06/20 add pick picture
            val intent = Intent()
            intent.action = android.content.Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivityForResult(intent,
                RESULT_GALLERY
            )
        }

        btn_upload.setOnClickListener {

            val data = CustomImageUtils.BitmapToString(bitmap)
            viewModel.uploadPembayaran(sharedPreferenceUtils.getIdUser, data)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult: ${requestCode} ${resultCode} ${data?.data}")
        when (requestCode) {
            RESULT_GALLERY -> {
                bitmap =
                    MediaStore.Images.Media.getBitmap(this.context?.contentResolver, data?.data)
                Glide.with(this).load(bitmap).into(img_bukti_pembayaran)
            }
        }
    }
}