package com.beone.kevin.ui.pelatih.profilecoach

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.ProfilePelatihModel
import com.beone.kevin.remote.model.StatusDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePelatihViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    private val profilePelatihModel:MutableLiveData<ProfilePelatihModel> = MutableLiveData()

    fun initdata():LiveData<ProfilePelatihModel> = profilePelatihModel

    private val statusDataModel:MutableLiveData<StatusDataModel> = MutableLiveData()

    fun initdata2():LiveData<StatusDataModel> = statusDataModel

    fun getProfile(iduser:String?){
        retrofitService.getProfilePelatih(iduser).enqueue(object : Callback<ProfilePelatihModel>{
            override fun onFailure(call: Call<ProfilePelatihModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ProfilePelatihModel>,
                response: Response<ProfilePelatihModel>
            ) {
                if (response.isSuccessful)
                    profilePelatihModel.postValue(response.body())
            }

        })
    }

    fun updatefoto(iduser:String?, foto:String?){
        retrofitService.updateFotoPelatih(iduser, foto).enqueue(object : Callback<StatusDataModel>{
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