package com.beone.kevin.ui.hrd.approvetkipayment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beone.kevin.CustomImageUtils
import com.beone.kevin.R
import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.ui.hrd.approvetkidocument.ApproveUserDocumentFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.approve_user_document_fragment.*
import kotlinx.android.synthetic.main.approve_user_payment_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ApproveUserPaymentFragment : Fragment() {

    companion object {
        fun newInstance() = ApproveUserPaymentFragment()
        private const val TAG = "ApproveUserPaymentFragment"
    }

    private val viewModel: ApproveUserPaymentViewModel by sharedViewModel()
    private val sharedPreferenceUtils: SharedPreferenceUtils by inject()
    private lateinit var iduser: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.approve_user_payment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        iduser = arguments?.getString("id").toString()
        val nama = arguments?.getString("nama")
        tv_namapayment.text = nama

        viewModel.initDataUserPayment().observe(viewLifecycleOwner, Observer {
            Glide.with(this)
                .load(CustomImageUtils.stringToBitmap(it.img_bukti))
                .error(android.R.color.background_dark)
                .into(img_payment)
        })

        viewModel.initDataStatus().observe(viewLifecycleOwner, Observer {
            if (it.status.equals("1")) {

                Toast.makeText(this.requireContext(), "Payment Disetujui", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        viewModel.showPayment(iduser)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_approvepayment.setOnClickListener() {
            viewModel.approvePayment(iduser, sharedPreferenceUtils.getIdUser, "1")
            this.findNavController().popBackStack()
        }

        btn_declinepayment.setOnClickListener {
            this.findNavController().popBackStack()
        }
    }


}