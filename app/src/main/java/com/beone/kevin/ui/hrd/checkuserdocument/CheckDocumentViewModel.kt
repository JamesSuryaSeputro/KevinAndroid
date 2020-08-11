package com.beone.kevin.ui.hrd.checkuserdocument

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.CheckUserDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckDocumentViewModel(val retrofitService: RetrofitService) : ViewModel() {
    // TODO: Implement the ViewModel
    companion object {
        private const val TAG = "CheckDocumentViewModel"
    }

    private val data: MutableLiveData<CheckUserDataModel> = MutableLiveData()

    fun initCheckDataUser(): LiveData<CheckUserDataModel> {
        return data
    }

    fun showUser(){
        retrofitService.getUserData().enqueue(object :
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