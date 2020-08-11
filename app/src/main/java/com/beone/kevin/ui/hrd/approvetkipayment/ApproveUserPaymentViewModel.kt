package com.beone.kevin.ui.hrd.approvetkipayment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.CheckDocumentModel
import com.beone.kevin.remote.model.InfoPembayaranModel
import com.beone.kevin.remote.model.StatusDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApproveUserPaymentViewModel(val retrofitService: RetrofitService) : ViewModel() {
    companion object {
        private const val TAG = "ApproveUserPaymentViewModel"
    }

    private val dataPayment: MutableLiveData<InfoPembayaranModel> = MutableLiveData()
    private val dataStatus: MutableLiveData<StatusDataModel> = MutableLiveData()

    fun initDataUserPayment(): LiveData<InfoPembayaranModel> {
        return dataPayment
    }

    fun initDataStatus(): LiveData<StatusDataModel> {
        return dataStatus
    }

    fun showPayment(iduser: String?){
        retrofitService.getUserPayment(iduser).enqueue(object :
            Callback<InfoPembayaranModel> {
            override fun onFailure(call: Call<InfoPembayaranModel>, t: Throwable) {
                Log.e(TAG, "onFailure1: ",t )
            }

            override fun onResponse(
                call: Call<InfoPembayaranModel>,
                response: Response<InfoPembayaranModel>
            ) {
                if (response.isSuccessful && response.body()!= null){
                    dataPayment.postValue(response.body())
                }
            }
        })
    }

    fun approvePayment(iduser: String?, idpegawai: String?, status: String?){
        retrofitService.approveUserPayment(iduser, idpegawai, status).enqueue(object :
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