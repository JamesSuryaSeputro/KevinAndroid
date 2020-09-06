package com.beone.kevin.ui.registertki

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.ProfileUserModel
import com.beone.kevin.remote.model.StatusDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterTkiViewModel(val retrofitService: RetrofitService) : ViewModel() {

    companion object {
        private const val TAG = "RegisterTkiViewModel"
    }

    private var data : MutableLiveData<StatusDataModel> = MutableLiveData()
    fun initDataRegisterTki():LiveData<StatusDataModel>{
        return data
    }

    private var data2 : MutableLiveData<ProfileUserModel> = MutableLiveData()
    fun initDataProfileUser():LiveData<ProfileUserModel>{
        return data2
    }

    fun registerTki(username: String?, password: String?, nama: String?, no_passport: String?, no_ktp: String?, tempatlahir: String?,
    tanggallahir: String?, kewarganegaraan: String?, jeniskelamin: String?, alamat: String?, notelp: String?, passfoto:String?, ttdfoto:String?){
        retrofitService.registerTki(username, password, nama, no_passport, no_ktp, tempatlahir, tanggallahir, kewarganegaraan, jeniskelamin,
        alamat, notelp, passfoto, ttdfoto).enqueue(object :
            Callback<StatusDataModel> {
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ",t )
            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {
                if (response.isSuccessful && response.body()!= null){
                    data.postValue(response.body())
                }
            }
        })
        }


}