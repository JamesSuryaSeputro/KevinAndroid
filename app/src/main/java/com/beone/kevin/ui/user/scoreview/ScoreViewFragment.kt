package com.beone.kevin.ui.user.scoreview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beone.kevin.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScoreViewFragment : Fragment() {

    companion object {
        fun newInstance() = ScoreViewFragment()
    }

    private val viewModel: ScoreViewViewModel by viewModel<ScoreViewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.score_view_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}