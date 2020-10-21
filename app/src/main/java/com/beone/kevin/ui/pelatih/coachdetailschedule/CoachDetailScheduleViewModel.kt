package com.beone.kevin.ui.pelatih.coachdetailschedule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.DetailJadwalPelatihModel
import com.beone.kevin.remote.model.JadwalPelatihModel
import com.beone.kevin.remote.model.StatusDataModel
import com.beone.kevin.ui.pelatih.DayEnum
import com.beone.kevin.ui.pelatih.schedulepelatih.SchedulePelatihViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoachDetailScheduleViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    companion object {
        private const val TAG = "CoachDetailScheduleViewModel"
    }

    private val data: MutableLiveData<DetailJadwalPelatihModel> = MutableLiveData()
    fun initData(): LiveData<DetailJadwalPelatihModel> = data
    private val data2: MutableLiveData<StatusDataModel> = MutableLiveData()
    fun initDialog():LiveData<StatusDataModel> = data2

    fun getDataDetailJadwal(idjadwal: String?){
        retrofitService.getCoachDetailSchedule(idjadwal).enqueue(object :
            Callback<DetailJadwalPelatihModel> {
            override fun onFailure(call: Call<DetailJadwalPelatihModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ",t )
            }

            override fun onResponse(
                call: Call<DetailJadwalPelatihModel>,
                response: Response<DetailJadwalPelatihModel>
            ) {
                data.postValue(response.body())
            }
        })
    }

    fun addDetailSchedule(idjadwal: String?, hari: DayEnum, tanggal: String, jammulai: String, jamselesai: String){

        retrofitService.addScheduleDetail(idjadwal, hari, tanggal, jammulai, jamselesai).enqueue(object :
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
                    getDataDetailJadwal(idjadwal)
                }else{
                    data2.postValue(StatusDataModel(0))
                }
            }
        })
    }

    fun deleteDetailData(idJadwal: String?, idDetailJadwal:String?){
        retrofitService.deleteCoachDetailSchedule(idDetailJadwal).enqueue(object : Callback<StatusDataModel>{
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ",t )
            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {
                if (response.isSuccessful ){
                    getDataDetailJadwal(idJadwal)
                }
            }

        })
    }
}