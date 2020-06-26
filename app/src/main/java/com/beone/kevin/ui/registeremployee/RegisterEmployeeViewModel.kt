package com.beone.kevin.ui.registeremployee

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.StatusDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterEmployeeViewModel(val retrofitService: RetrofitService) : ViewModel() {
    // TODO: Implement the ViewModel
    companion object {
        private const val TAG = "RegisterEmployeeViewModel"
    }

    private var data : MutableLiveData<StatusDataModel> = MutableLiveData()
    fun initDataRegisterEmployee(): LiveData<StatusDataModel> {
        return data
    }

    fun registerEmployee(username: String?, password: String?, namapegawai: String?, nip: String?){
        retrofitService.registerPegawai(username, password, namapegawai, nip).enqueue(object :
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