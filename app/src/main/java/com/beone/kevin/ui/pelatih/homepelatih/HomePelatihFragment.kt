package com.beone.kevin.ui.pelatih.homepelatih

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import kotlinx.android.synthetic.main.home_pelatih_fragment.*
import kotlinx.android.synthetic.main.home_user_fragment.*
import org.koin.android.ext.android.inject

class HomePelatihFragment : Fragment() {

    private val sharepreference: SharedPreferenceUtils by inject()

    companion object {
        fun newInstance(): HomePelatihFragment {
            val fragment = HomePelatihFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: HomePelatihViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_pelatih_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        when (sharepreference.getIdUser?.length) {
            1 -> {
                id_coach.text = "22" + sharepreference.getYearUser + "00" + sharepreference.getIdUser
            }
            2 -> {
                id_coach.text = "22" + sharepreference.getYearUser + "0" + sharepreference.getIdUser
            }
            else -> {
                id_coach.text = "22" + sharepreference.getYearUser + sharepreference.getIdUser
            }
        }
        tv_homenamecoach.text = sharepreference.getNamaUser
    }

}