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


}