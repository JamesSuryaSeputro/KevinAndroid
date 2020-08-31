package com.beone.kevin.ui.registertki

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.CheckUserDataModel
import com.beone.kevin.remote.model.RegisterTKIModel
import com.beone.kevin.remote.model.StatusDataModel
import com.beone.kevin.ui.hrd.checkuserpayment.CheckPembayaranViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

class RegisterTkiViewModel(val retrofitService: RetrofitService) : ViewModel() {

    companion object {
        private const val TAG = "RegisterTkiViewModel"
    }

    private var data : MutableLiveData<StatusDataModel> = MutableLiveData()
    fun initDataRegisterTki():LiveData<StatusDataModel>{
        return data
    }

    private var data2 : MutableLiveData<RegisterTKIModel> = MutableLiveData()
    fun initDataProfileUser():LiveData<RegisterTKIModel>{
        return data2
    }

    fun registerTki(a:RegisterTKIModel? ){
            retrofitService.registerTki(a?.username, a?.password, a?.nama, a?.no_passport, a?.no_ktp, a?.tempatlahir, a?.tanggallahir,
                a?.kewarganegaraan, a?.jeniskelamin, a?.alamat, a?.notelp, a?.passfoto, a?.ttdfoto).enqueue(
                object : Callback<StatusDataModel> {
                    override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                        Log.e(TAG, "onFailure: ", t)
                    }

                    override fun onResponse(
                        call: Call<StatusDataModel>,
                        response: Response<StatusDataModel>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            data.postValue(response.body())
                            Log.d(TAG, "onResponse: successful")
                        }
                    }
                })
        }

    fun showProfileUser(a:RegisterTKIModel? ){
        retrofitService.getProfilUser(a?.username, a?.password, a?.nama, a?.no_passport, a?.no_ktp, a?.tempatlahir, a?.tanggallahir,
            a?.kewarganegaraan, a?.jeniskelamin, a?.alamat, a?.notelp, a?.passfoto, a?.ttdfoto).enqueue(object :
            Callback<RegisterTKIModel> {
            override fun onFailure(call: Call<RegisterTKIModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ",t )
            }

            override fun onResponse(
                call: Call<RegisterTKIModel>,
                response: Response<RegisterTKIModel>
            ) {
                if (response.isSuccessful && response.body()!= null){
                    data2.postValue(response.body())
                }
            }
        })
    }
}