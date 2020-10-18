package com.beone.kevin.ui.pelatih.coachdetailschedule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.DetailJadwalPelatihModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoachDetailScheduleViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    companion object {
        private const val TAG = "CoachDetailScheduleViewModel"
    }

    private val data: MutableLiveData<DetailJadwalPelatihModel> = MutableLiveData()
    fun initData(): LiveData<DetailJadwalPelatihModel> = data

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
}