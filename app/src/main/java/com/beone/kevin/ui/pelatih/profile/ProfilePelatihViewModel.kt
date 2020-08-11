package com.beone.kevin.ui.pelatih.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.ProfilePelatihModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePelatihViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    private val profilePelatihModel:MutableLiveData<ProfilePelatihModel> = MutableLiveData()

    fun initdata():LiveData<ProfilePelatihModel> =  profilePelatihModel

    fun getprofile(iduser:String?){
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
}