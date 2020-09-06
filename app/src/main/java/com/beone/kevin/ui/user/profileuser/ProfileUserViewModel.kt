package com.beone.kevin.ui.user.profileuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.ProfilePelatihModel
import com.beone.kevin.remote.model.ProfileUserModel
import com.beone.kevin.remote.model.StatusDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileUserViewModel(val retrofitService: RetrofitService) : ViewModel() {

    private val profileUserModel: MutableLiveData<ProfileUserModel> = MutableLiveData()

    fun initdata(): LiveData<ProfileUserModel> = profileUserModel

    fun getProfile(iduser:String?){
        retrofitService.getProfileUser(iduser).enqueue(object : Callback<ProfileUserModel>{
            override fun onFailure(call: Call<ProfileUserModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ProfileUserModel>,
                response: Response<ProfileUserModel>
            ) {
                if (response.isSuccessful && response.body()!= null){
                    profileUserModel.postValue(response.body())
                }
            }
        })
    }
}