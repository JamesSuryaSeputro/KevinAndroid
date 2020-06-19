package com.beone.kevin.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.StatusLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LoginsViewModel(val retrofitService: RetrofitService) : ViewModel() {
    // TODO: Implement the ViewModel

    var data:MutableLiveData<StatusLogin> = MutableLiveData()
    fun getRetrofitServiceHash():String = retrofitService.hashCode().toString()

    fun initLiveDataLogin():LiveData<StatusLogin> = data

    fun loginUser(username:String?, password:String?){
        retrofitService.checkLoginUser(username,password).enqueue(object : Callback<StatusLogin>{
            override fun onFailure(call: Call<StatusLogin>, t: Throwable) {
               data.value?.isFailedFetch = true
            }

            override fun onResponse(call: Call<StatusLogin>, response: Response<StatusLogin>) {
                data.value?.TypeLogin = TypeLoginEnum.TKI
               if (response!=null){
                    data.value = response.body()
               }else{
                   data.value?.isFailedFetch = true
               }
            }

        })
    }

    fun loginPegawai(username:String?, password:String?){
        retrofitService.checkLoginUser(username,password).enqueue(object : Callback<StatusLogin>{
            override fun onFailure(call: Call<StatusLogin>, t: Throwable) {
                data.value?.isFailedFetch = true
            }

            override fun onResponse(call: Call<StatusLogin>, response: Response<StatusLogin>) {
                data.value?.TypeLogin = TypeLoginEnum.PEGAWAI
                if (response!=null){
                    data.value = response.body()
                }else{
                    data.value?.isFailedFetch = true
                }
            }

        })
    }

    fun loginPelatih(username:String?, password:String?) {
        retrofitService.checkLoginUser(username,password).enqueue(object : Callback<StatusLogin>{
            override fun onFailure(call: Call<StatusLogin>, t: Throwable) {
                data.value?.isFailedFetch = true
            }

            override fun onResponse(call: Call<StatusLogin>, response: Response<StatusLogin>) {
                data.value?.TypeLogin = TypeLoginEnum.PELATIH
                if (response!=null){
                    data.value = response.body()
                }else{
                    data.value?.isFailedFetch = true
                }
            }

        })
    }

}