package com.beone.kevin.ui.registercoach

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.StatusDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterCoachViewModel(val retrofitService: RetrofitService) : ViewModel() {
    // TODO: Implement the ViewModel
    companion object {
        private const val TAG = "RegisterCoachViewModel"
    }

    private var data : MutableLiveData<StatusDataModel> = MutableLiveData()
    fun initDataRegisterCoach(): LiveData<StatusDataModel> {
        return data
    }

    fun registerCoach(username: String?, password: String?, namapelatih: String?){
        retrofitService.registerPelatih(username, password, namapelatih).enqueue(object :
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