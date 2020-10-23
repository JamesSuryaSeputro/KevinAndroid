package com.beone.kevin.ui.hrd.checkuserpayment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.CheckUserDataModel
import com.beone.kevin.ui.hrd.checkuserdocument.CheckDocumentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckPembayaranViewModel(val retrofitService: RetrofitService) : ViewModel() {

    companion object {
        private const val TAG = "CheckPembayaranViewModel"
    }

    private val data: MutableLiveData<CheckUserDataModel> = MutableLiveData()

    fun initData(): LiveData<CheckUserDataModel> {
        return data
    }

    fun showUser(){
        retrofitService.getUserPaymentData().enqueue(object :
            Callback<CheckUserDataModel> {
            override fun onFailure(call: Call<CheckUserDataModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ",t )
            }

            override fun onResponse(
                call: Call<CheckUserDataModel>,
                response: Response<CheckUserDataModel>
            ) {
                if (response.isSuccessful && response.body()!= null){
                    data.postValue(response.body())
                }
            }
        })
    }

}