package com.beone.kevin.ui.user.homeuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import kotlinx.android.synthetic.main.home_user_fragment.*
import org.koin.android.ext.android.inject

class HomeUserFragment : Fragment() {

    private val sharepreference: SharedPreferenceUtils by inject()

    companion object {
        fun newInstance(): HomeUserFragment {
            val fragment = HomeUserFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: HomeUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        id_user.text = sharepreference.getIdUser
        tv_homename.text = sharepreference.getNamaUser
    }

}