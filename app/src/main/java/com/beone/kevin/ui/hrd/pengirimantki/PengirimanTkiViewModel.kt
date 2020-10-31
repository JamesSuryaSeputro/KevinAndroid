package com.beone.kevin.ui.hrd.pengirimantki

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.CheckUserDataModel
import com.beone.kevin.remote.model.ShippingUserModel
import com.beone.kevin.ui.hrd.datatki.DataTkiViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PengirimanTkiViewModel(val retrofitService: RetrofitService) : ViewModel() {

    companion object {
        private const val TAG = "PengirimanTkiViewModel"
    }

    private val data: MutableLiveData<ShippingUserModel> = MutableLiveData()

    fun initData(): LiveData<ShippingUserModel> {
        return data
    }

    fun getDataPengirimanTki() {
        retrofitService.getPengirimanTki().enqueue(object :
            Callback<ShippingUserModel> {
            override fun onFailure(call: Call<ShippingUserModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(
                call: Call<ShippingUserModel>,
                response: Response<ShippingUserModel>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    data.postValue(response.body())
                }
            }
        })
    }
}