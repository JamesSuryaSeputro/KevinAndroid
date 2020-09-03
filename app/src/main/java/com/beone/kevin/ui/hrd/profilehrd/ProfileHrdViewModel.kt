package com.beone.kevin.ui.hrd.profilehrd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.ProfilePegawaiModel
import com.beone.kevin.remote.model.StatusDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileHrdViewModel(private val retrofitService: RetrofitService): ViewModel() {

    private val profilePegawaiModel: MutableLiveData<ProfilePegawaiModel> = MutableLiveData()

    fun initdata(): LiveData<ProfilePegawaiModel> = profilePegawaiModel

    private val statusDataModel: MutableLiveData<StatusDataModel> = MutableLiveData()

    fun initdata2(): LiveData<StatusDataModel> = statusDataModel

    fun getprofile(iduser:String?){
        retrofitService.getProfilePegawai(iduser).enqueue(object : Callback<ProfilePegawaiModel> {
            override fun onFailure(call: Call<ProfilePegawaiModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ProfilePegawaiModel>,
                response: Response<ProfilePegawaiModel>
            ) {
                if (response.isSuccessful)
                    profilePegawaiModel.postValue(response.body())
            }
        })
    }

    fun updatefoto(iduser:String?, foto:String?){
        retrofitService.updateFotoPegawai(iduser, foto).enqueue(object : Callback<StatusDataModel> {
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {
                if (response.isSuccessful)
                    statusDataModel.postValue(response.body())
            }
        })
    }
}