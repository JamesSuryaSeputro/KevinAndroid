package com.beone.kevin.ui.hrd.checkuserpayment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beone.kevin.R
import com.beone.kevin.ui.hrd.checkuserdocument.OnClickUser
import kotlinx.android.synthetic.main.check_document_fragment.*
import org.koin.android.ext.android.inject

class CheckPembayaranFragment : Fragment(), OnClickUser {

    companion object {
        fun newInstance() =
            CheckPembayaranFragment()
    }

    private val viewModel: CheckPembayaranViewModel by inject()
    private val paymentAdapter: UserPaymentAdapter = UserPaymentAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.check_pembayaran_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rcv_user.adapter = paymentAdapter

        viewModel.initData().observe(viewLifecycleOwner, Observer {
            paymentAdapter.swapData(it)
        })

        viewModel.showUser()
    }

    override fun onClick(id: String?, nama: String?) {
        val bundle = bundleOf("id" to id, "nama" to nama)
        this.findNavController()
            .navigate(R.id.action_checkPembayaranFragment_to_approveUserPaymentFragment, bundle)
    }
}