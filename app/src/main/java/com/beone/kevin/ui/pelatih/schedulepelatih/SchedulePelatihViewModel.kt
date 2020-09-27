package com.beone.kevin.ui.pelatih.schedulepelatih

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.JadwalPelatihModel
import com.beone.kevin.remote.model.StatusDataModel
import com.beone.kevin.ui.pelatih.DayEnum
import com.beone.kevin.ui.pelatih.SubjectEnum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchedulePelatihViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    private val data: MutableLiveData<JadwalPelatihModel> = MutableLiveData()
    private val data2: MutableLiveData<StatusDataModel> = MutableLiveData()

    fun initData():LiveData<JadwalPelatihModel> = data

    fun initDialog():LiveData<StatusDataModel> = data2


    fun getData(iduser:String?){
        Log.d(TAG, "getData: ${iduser}")
        retrofitService.getSchedullerAllPelatih(iduser).enqueue(object : Callback<JadwalPelatihModel>{
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

    fun deleteData(iduser: String?,idSubject:String?){
        retrofitService.deleteSchedulePelatih(idSubject).enqueue(object : Callback<StatusDataModel>{
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ",t )
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

    fun addSchedule(iduser: String?, idsubject: SubjectEnum, hari: DayEnum, tglmulai: String, tglselesai: String, jammulai: String, jamselesai: String){

        retrofitService.addSchedule(iduser,idsubject.subjectdbposition,hari,tglmulai,tglselesai,jammulai,jamselesai).enqueue(object :
            Callback<StatusDataModel>{
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ",t )
            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {
                if (response.isSuccessful){
                    data2.postValue(StatusDataModel(1))
                    getData(iduser)
                }else{
                    data2.postValue(StatusDataModel(0))
                }
            }
        })
    }

    companion object {
        private const val TAG = "SchedulePelatihViewModel"
    }
}