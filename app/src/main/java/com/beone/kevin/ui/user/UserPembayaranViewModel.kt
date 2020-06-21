package com.beone.kevin.ui.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.InfoPembayaranModel
import com.beone.kevin.remote.model.StatusDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPembayaranViewModel(val retrofitService: RetrofitService) : ViewModel() {
    private var data : MutableLiveData<InfoPembayaranModel> = MutableLiveData()
//    private var data2 : MutableLiveData<StatusDataModel> = MutableLiveData()

    fun initDataPembayaran():LiveData<InfoPembayaranModel>{
        return data;
    }


    fun uploadPembayaran(idUser: String?,image:String?){
        retrofitService.uploadPembayaran(idUser,image).enqueue(object : Callback<StatusDataModel>{
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {
                if (response.isSuccessful){
                    checkPembayaran(idUser)
                }
            }

        })
    }


    fun checkPembayaran(idUser:String?){

        retrofitService.checkPembayaran(idUser).enqueue(object : Callback<InfoPembayaranModel>{
            override fun onFailure(call: Call<InfoPembayaranModel>, t: Throwable) {
                Log.e(Companion.TAG, "onFailure: ",t )
            }

            override fun onResponse(
                call: Call<InfoPembayaranModel>,
                response: Response<InfoPembayaranModel>
            ) {
                if (response.isSuccessful && response.body()!= null){
                    data.postValue(response.body())
                }
            }

        })


    }

    companion object {
        private const val TAG = "UserPembayaranViewModel"
    }

}