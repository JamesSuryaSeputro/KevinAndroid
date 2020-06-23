package com.beone.kevin.ui.user.uploaddocument

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.CheckDocumentModel
import com.beone.kevin.remote.model.StatusDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UploadDocumentViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    private val data: MutableLiveData<CheckDocumentModel> = MutableLiveData()

    fun initLiveData(): LiveData<CheckDocumentModel> = data

    fun getData(iduser: String?) {
        retrofitService.checkDocUser(iduser).enqueue(object : Callback<CheckDocumentModel> {
            override fun onFailure(call: Call<CheckDocumentModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<CheckDocumentModel>,
                response: Response<CheckDocumentModel>
            ) {
                if (response.isSuccessful) {
                    data.postValue(response.body())
                }
            }

        })
    }

    fun uploadDocUser(
        iduser: String?,
        scanKtp: String?,
        scanKompensasi: String?,
        scanKesehatan: String?,
        scanSuratKerja: String?
    ) {

        retrofitService.uploadDocUser(iduser,
            scanKtp,
            scanKompensasi,
            scanKesehatan,
            scanSuratKerja
        ).enqueue(object : Callback<StatusDataModel>{
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {
                if (response.isSuccessful){
                    getData(iduser)
                }
            }
        })

    }

}