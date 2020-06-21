package com.beone.kevin.ui.user.uploaddocument

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UploadDocumentFragment : Fragment() {

    companion object {
        fun newInstance() =
            UploadDocumentFragment()
    }

    private val viewModel: UploadDocumentViewModel by viewModel<UploadDocumentViewModel>()
    private val sharedPreferenceUtils:SharedPreferenceUtils by inject<SharedPreferenceUtils>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.upload_document_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel

        viewModel.initLiveData().observe(viewLifecycleOwner, Observer {
            // TODO: 21/06/20 all have observe to edt and next ...
        })

        viewModel.getData(sharedPreferenceUtils.getIdUser)


    }

}