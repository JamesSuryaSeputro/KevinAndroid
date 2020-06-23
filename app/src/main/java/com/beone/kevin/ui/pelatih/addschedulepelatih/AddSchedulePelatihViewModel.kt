package com.beone.kevin.ui.pelatih.addschedulepelatih

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.StatusDataModel
import com.beone.kevin.ui.pelatih.DayEnum
import com.beone.kevin.ui.pelatih.SubjectEnum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddSchedulePelatihViewModel(private val retrofitService: RetrofitService):ViewModel() {

   private val data:MutableLiveData<StatusDataModel> = MutableLiveData()

    fun initLiveData(): LiveData<StatusDataModel> = data

   fun addSchedule(idpelatih:String?, idsubject: SubjectEnum, hari: DayEnum){
       // TODO: 22/06/20 add jadwal
       retrofitService.addSchedule(idpelatih,idsubject.subjectdbposition,hari).enqueue(object :
           Callback<StatusDataModel>{
           override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
           }

           override fun onResponse(
               call: Call<StatusDataModel>,
               response: Response<StatusDataModel>
           ) {
               if (response.isSuccessful){
                   data.postValue(StatusDataModel(1))
               }else{
                   data.postValue(StatusDataModel(0))
               }

           }
       })
   }
}