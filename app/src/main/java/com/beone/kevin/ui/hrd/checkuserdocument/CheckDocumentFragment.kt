package com.beone.kevin.ui.hrd.checkuserdocument

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beone.kevin.R
import kotlinx.android.synthetic.main.check_document_fragment.*
import org.koin.android.ext.android.inject

class CheckDocumentFragment : Fragment(), OnClickUser {

    companion object {
        fun newInstance() = CheckDocumentFragment()
        private const val TAG = "CheckDocumentFragment"
    }

    private val viewModel: CheckDocumentViewModel by inject()
    private val documentAdapter: UserDocumentAdapter = UserDocumentAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.check_document_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rcv_user.adapter = documentAdapter

        viewModel.initData().observe(viewLifecycleOwner, Observer {
            documentAdapter.swapData(it)
        })

        viewModel.showUser()
    }

    override fun onClick(id: String?, nama: String?) {
        val bundle = bundleOf("id" to id, "nama" to nama)
        this.findNavController()
            .navigate(R.id.action_checkDocumentFragment_to_approveUserDocumentFragment, bundle)
    }
}