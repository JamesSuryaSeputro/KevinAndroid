package com.beone.kevin.ui.user.uploaddocument

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.CheckDocumentModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UploadDocumentViewModel(val retrofitService: RetrofitService) : ViewModel() {
    // TODO: Implement the ViewModel

    private val data: MutableLiveData<CheckDocumentModel>  = MutableLiveData()

    fun initLiveData():LiveData<CheckDocumentModel> = data

    fun getData(iduser:String?){
        retrofitService.checkDocUser(iduser).enqueue(object : Callback<CheckDocumentModel>{
            override fun onFailure(call: Call<CheckDocumentModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<CheckDocumentModel>,
                response: Response<CheckDocumentModel>
            ) {
                if (response.isSuccessful){
                    data.postValue(response.body())
                }
            }

        })
    }


}