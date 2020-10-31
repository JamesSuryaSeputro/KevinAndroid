package com.beone.kevin.ui.pelatih.profilecoach

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.beone.kevin.CustomImageUtils
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.profile_pelatih_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProfilePelatihFragment : Fragment() {

    companion object {
        fun newInstance() = ProfilePelatihFragment()
        private const val RESULT_FOTO = 12
    }

    private val viewModel: ProfilePelatihViewModel by sharedViewModel()
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject()
    private var bitmap: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_pelatih_fragment, container, false)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }

    fun hideBottomNav(){
        val navView: BottomNavigationView = activity?.findViewById(R.id.nav_view_coach)!!
        navView.visibility = View.GONE
    }

    fun showBottomNav(){
        val navView: BottomNavigationView = activity?.findViewById(R.id.nav_view_coach)!!
        navView.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideBottomNav()

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    showBottomNav()
                    setHasOptionsMenu(false)
                    view.findNavController().navigate(R.id.action_item_profile_to_navigation_coachhome)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        viewModel.initdata().observe(viewLifecycleOwner, Observer {
            coach_name.text = it.nama_pelatih
            coach_username.text = it.username
            active_sincecoach.text = it.year
            Glide.with(this)
                .load(CustomImageUtils.stringToBitmap(it.foto))
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .error(android.R.color.background_dark)
                .into(img_view_coachprofile)
        })

        viewModel.initdata2().observe(viewLifecycleOwner, Observer {
            if (it.status.equals(1)) {

                Toast.makeText(this.requireContext(), "Berhasil ubah foto", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        btn_pick_coachimg.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, RESULT_FOTO)
        }

        viewModel.getProfile(sharedPreferenceUtils.getIdUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            when (requestCode) {
                RESULT_FOTO -> {
                    bitmap =
                        MediaStore.Images.Media.getBitmap(this.context?.contentResolver, data?.data)
                    if (bitmap != null) {
                        var foto: String? = ""
                        foto = bitmap?.let { it1 -> CustomImageUtils.BitmapToString(it1) }
                        viewModel.updatefoto(sharedPreferenceUtils.getIdUser, foto)

                        Glide.with(this)
                            .load(foto?.let { CustomImageUtils.stringToBitmap(it) })
                            .error(android.R.color.background_dark)
                            .into(img_view_coachprofile)
                    }
                }
            }
        }
    }
}