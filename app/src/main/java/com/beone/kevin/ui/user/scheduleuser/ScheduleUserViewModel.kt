package com.beone.kevin.ui.user.scheduleuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.JadwalPelatihModel
import com.beone.kevin.remote.model.JadwalPelatihModelItem
import com.beone.kevin.remote.model.StatusDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleUserViewModel(val retrofitService: RetrofitService) : ViewModel() {

    companion object {
        private const val TAG = "ScheduleUserViewModel"
    }

    private val data: MutableLiveData<JadwalPelatihModel> = MutableLiveData()
    private val data2: MutableLiveData<StatusDataModel> = MutableLiveData()

    fun initData(): LiveData<JadwalPelatihModel> = data

    fun initDialog(): LiveData<StatusDataModel> = data2

    fun getDataScheduleUser(iduser:String?){
        Log.d(TAG, "getData: ${iduser}")
        retrofitService.getScheduleUser(iduser).enqueue(object :
            Callback<JadwalPelatihModel> {
            override fun onFailure(call: Call<JadwalPelatihModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ",t )
            }

            override fun onResponse(
                call: Call<JadwalPelatihModel>,
                response: Response<JadwalPelatihModel>
            ) {
                if (response.isSuccessful){
                    data.postValue(response.body())
                    data2.postValue(StatusDataModel(0))
                }
            }
        })
    }
}