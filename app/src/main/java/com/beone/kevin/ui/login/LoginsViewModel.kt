package com.beone.kevin.ui.login

import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import retrofit2.Retrofit

class LoginsViewModel(val retrofitService: RetrofitService) : ViewModel() {
    // TODO: Implement the ViewModel

    fun getRetrofitServiceHash():String = retrofitService.hashCode().toString()


}