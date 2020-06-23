package com.beone.kevin.ui.pelatih.schedulepelatih

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.JadwalPelatihModel
import com.beone.kevin.remote.model.StatusDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchedulePelatihViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    private val data: MutableLiveData<JadwalPelatihModel> = MutableLiveData()

    fun initData():LiveData<JadwalPelatihModel> = data


    fun getData(iduser:String?){
        retrofitService.getSchedullerAllPelatih(iduser).enqueue(object : Callback<JadwalPelatihModel>{
            override fun onFailure(call: Call<JadwalPelatihModel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<JadwalPelatihModel>,
                response: Response<JadwalPelatihModel>
            ) {
                if (response.isSuccessful){
                    data.postValue(response.body())
                }
            }

        })
    }

    fun deleteData(iduser: String?,idSubject:String?){
        retrofitService.deleteSchedulePelatih(idSubject).enqueue(object : Callback<StatusDataModel>{
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {
                if (response.isSuccessful ){
                    getData(iduser)
                }
            }

        })
    }
}