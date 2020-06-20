package com.beone.kevin.ui.login


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.beone.kevin.R
import kotlinx.android.synthetic.main.logins_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginsFragment : Fragment() {

    companion object {
        private const val TAG = "LoginsFragment"
        fun newInstance() = LoginsFragment()
    }

    private val vm: LoginsViewModel by viewModel<LoginsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.logins_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(LoginsViewModel::class.java)
        Log.d(TAG, "onActivityCreated: ${vm.getRetrofitServiceHash()}")
        btn_register.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_loginsFragment_to_registerFragment)
        }

    }

}