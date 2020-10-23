package com.beone.kevin.ui.hrd.datatki

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.CheckUserDataModel
import com.beone.kevin.remote.model.CheckUserDataModelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataTkiViewModel(val retrofitService: RetrofitService) : ViewModel() {

    companion object {
        private const val TAG = "DataTkiViewModel"
    }

    private val data: MutableLiveData<CheckUserDataModel> = MutableLiveData()

    fun initData(): LiveData<CheckUserDataModel> {
        return data
    }

    private val data2: MutableLiveData<List<CheckUserDataModelItem>> = MutableLiveData()

    fun initData2(): LiveData<List<CheckUserDataModelItem>> {
        return data2
    }

    var isLoading = MutableLiveData<Boolean>()

    fun showUser(){
        retrofitService.getDataTkiUser().enqueue(object :
            Callback<CheckUserDataModel> {
            override fun onFailure(call: Call<CheckUserDataModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(
                call: Call<CheckUserDataModel>,
                response: Response<CheckUserDataModel>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    data.postValue(response.body())
                    loadingFinished()
                }
            }
        })
    }

    fun loadingFinished(){
        isLoading.value = true
    }

    fun searchUser(nama: String?){
        retrofitService.searchDataTkiUser(nama).enqueue(object :
            Callback<List<CheckUserDataModelItem>> {
            override fun onFailure(call: Call<List<CheckUserDataModelItem>>, t: Throwable) {
                Log.e(TAG, "onFailure2: ", t)
            }

            override fun onResponse(
                call: Call<List<CheckUserDataModelItem>>,
                response: Response<List<CheckUserDataModelItem>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    data2.postValue(response.body())
                }
            }
        })
    }
}