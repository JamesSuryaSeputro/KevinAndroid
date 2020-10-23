package com.beone.kevin.ui.hrd.detaildatatki

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.CheckUserDataModel
import com.beone.kevin.remote.model.DataTkiModel
import com.beone.kevin.ui.hrd.datatki.DataTkiViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailDataTkiViewModel(val retrofitService: RetrofitService) : ViewModel() {

    companion object {
        private const val TAG = "DetailDataTkiViewModel"
    }

    private val data: MutableLiveData<DataTkiModel> = MutableLiveData()

    fun initData(): LiveData<DataTkiModel> {
        return data
    }

    fun getData(iduser: String?){
        retrofitService.getDetailDataTki(iduser).enqueue(object :
            Callback<DataTkiModel> {
            override fun onFailure(call: Call<DataTkiModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ",t )
            }

            override fun onResponse(
                call: Call<DataTkiModel>,
                response: Response<DataTkiModel>
            ) {
                if (response.isSuccessful && response.body()!= null){
                    data.postValue(response.body())
                }
            }
        })
    }
}