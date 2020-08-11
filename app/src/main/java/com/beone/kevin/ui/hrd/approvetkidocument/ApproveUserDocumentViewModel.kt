package com.beone.kevin.ui.hrd.approvetkidocument

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.CheckDocumentModel
import com.beone.kevin.remote.model.StatusDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApproveUserDocumentViewModel(val retrofitService: RetrofitService) : ViewModel() {

    companion object {
        private const val TAG = "ApproveUserDocumentViewModel"
    }

    private val dataDokumen: MutableLiveData<CheckDocumentModel> = MutableLiveData()
    private val dataStatus: MutableLiveData<StatusDataModel> = MutableLiveData()

    fun initDataUserDocument(): LiveData<CheckDocumentModel> {
        return dataDokumen
    }

    fun initDataStatus(): LiveData<StatusDataModel> {
        return dataStatus
    }

    fun showDocument(iduser: String?){
        retrofitService.getUserDocument(iduser).enqueue(object :
            Callback<CheckDocumentModel> {
            override fun onFailure(call: Call<CheckDocumentModel>, t: Throwable) {
                Log.e(TAG, "onFailure1: ",t )
            }

            override fun onResponse(
                call: Call<CheckDocumentModel>,
                response: Response<CheckDocumentModel>
            ) {
                if (response.isSuccessful && response.body()!= null){
                    dataDokumen.postValue(response.body())
                }
            }
        })
    }

    fun approveDocument(iduser: String?, idpegawai: String?, status: String?){
        retrofitService.approveUserDocument(iduser, idpegawai, status).enqueue(object :
            Callback<StatusDataModel> {
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                Log.e(TAG, "onFailure2: ",t )
            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {
                if (response.isSuccessful && response.body()!= null){
                    dataStatus.postValue(response.body())
                }
            }
        })
    }
}