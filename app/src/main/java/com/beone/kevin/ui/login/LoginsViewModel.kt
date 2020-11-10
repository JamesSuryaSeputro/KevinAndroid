package com.beone.kevin.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.StatusDataModel
import com.beone.kevin.remote.model.StatusDataModel2
import com.beone.kevin.remote.model.StatusLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginsViewModel(val retrofitService: RetrofitService) : ViewModel() {

    var data: MutableLiveData<StatusLogin> = MutableLiveData()

    fun getRetrofitServiceHash(): String = retrofitService.hashCode().toString()

    fun initLiveDataLogin(): LiveData<StatusLogin> = data

//    var dataStatus: MutableLiveData<StatusDataModel> = MutableLiveData()
//
//    fun initDataStatus(): LiveData<StatusDataModel> = dataStatus

    fun loginUser(username: String?, password: String?) {
        retrofitService.checkLoginUser(username, password).enqueue(object : Callback<StatusLogin> {
            override fun onFailure(call: Call<StatusLogin>, t: Throwable) {
                data.value?.isFailedFetch = true
            }
            override fun onResponse(call: Call<StatusLogin>, response: Response<StatusLogin>) {

                if (response.isSuccessful && response.body()!=null) {
//                    checkPassedUser(iduser)
                    val tmpData = response.body()
                    tmpData?.TypeLogin = TypeLoginEnum.TKI.jenis
                    data.postValue(tmpData)
                } else {
                    data.value?.isFailedFetch = true
                }
            }
        })
    }

    fun loginPegawai(username: String?, password: String?) {

        retrofitService.checkLoginPegawai(username, password).enqueue(object : Callback<StatusLogin> {
            override fun onFailure(call: Call<StatusLogin>, t: Throwable) {
                data.value?.isFailedFetch = true
            }
            override fun onResponse(call: Call<StatusLogin>, response: Response<StatusLogin>) {

                if (response.isSuccessful && response.body()!=null) {
                    val tmpData = response.body()
                    tmpData?.TypeLogin = TypeLoginEnum.PEGAWAI.jenis
                    data.postValue(tmpData)
                } else {
                    data.value?.isFailedFetch = true
                }
            }
        })
    }

    fun loginPelatih(username: String?, password: String?) {

        retrofitService.checkLoginPelatih(username, password).enqueue(object : Callback<StatusLogin> {
            override fun onFailure(call: Call<StatusLogin>, t: Throwable) {
                data.value?.isFailedFetch = true
            }
            override fun onResponse(call: Call<StatusLogin>, response: Response<StatusLogin>) {

                if (response.isSuccessful && response.body()!=null) {
                    val tmpData = response.body()
                    tmpData?.TypeLogin = TypeLoginEnum.PELATIH.jenis
                    data.postValue(tmpData)
                } else {
                    data.value?.isFailedFetch = true
                }
            }
        })
    }

    fun checkPassedUserGreaterThan(iduser: String?) {

        retrofitService.checkPassedUserGreaterThan(iduser).enqueue(object : Callback<StatusDataModel2> {
            override fun onResponse(
                call: Call<StatusDataModel2>,
                response: Response<StatusDataModel2>
            ) {
                response.body()?.apply {
                    if (this.status.equals("1")) {
                        Log.d("checkpassed", "TKI")
                        updateStatusTki(iduser)
                    } else {
                        Log.d("checkpassed", "Calon TKI")
                        updateStatusCalonTki(iduser)
                    }
                }

            }

            override fun onFailure(call: Call<StatusDataModel2>, t: Throwable) {
                Log.e("checkpassed", "onFailure: $t")
            }

        })
    }

    fun checkPassedUserLowerThan(iduser: String?) {

        retrofitService.checkPassedUserLowerThan(iduser).enqueue(object : Callback<StatusDataModel2> {
            override fun onResponse(
                call: Call<StatusDataModel2>,
                response: Response<StatusDataModel2>
            ) {
                response.body()?.apply {
                    if (this.status.equals("0")) {
                        checkPassedUserGreaterThan(iduser)
                    } else {
                        updateStatusCalonTki(iduser)
                        Log.d("checkpassed", "Calon TKI")
                    }
                }

            }

            override fun onFailure(call: Call<StatusDataModel2>, t: Throwable) {
                Log.e("checkpassed", "onFailure: $t")
            }

        })
    }

    fun updateStatusTki(iduser: String?) {

        retrofitService.updateStatusTki(iduser).enqueue(object : Callback<StatusDataModel2> {
            override fun onResponse(
                call: Call<StatusDataModel2>,
                response: Response<StatusDataModel2>
            ) {
                Log.d("checkpassed", "onResponse: " + response.body()!!.status)
            }

            override fun onFailure(call: Call<StatusDataModel2>, t: Throwable) {
                Log.e("checkpassed", "onFailure: $t")
            }

        })
    }

    fun updateStatusCalonTki(iduser: String?) {

        retrofitService.updateStatusCalonTki(iduser).enqueue(object : Callback<StatusDataModel2> {
            override fun onResponse(
                call: Call<StatusDataModel2>,
                response: Response<StatusDataModel2>
            ) {
                Log.d("checkpassed", "onResponse: " + response.body()!!.status)
            }

            override fun onFailure(call: Call<StatusDataModel2>, t: Throwable) {
                Log.e("checkpassed", "onFailure: $t")
            }

        })
    }
}