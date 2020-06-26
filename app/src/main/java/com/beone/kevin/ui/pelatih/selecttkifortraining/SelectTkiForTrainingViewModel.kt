package com.beone.kevin.ui.pelatih.selecttkifortraining

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.StatusDataModel
import com.beone.kevin.remote.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectTkiForTrainingViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    private val dataFragment: MutableLiveData<UserModel> = MutableLiveData()
    private val dataDialog:MutableLiveData<UserModel> = MutableLiveData()
    private val dataStatus:MutableLiveData<StatusDataModel> = MutableLiveData()

    fun initFragment(): LiveData<UserModel> =  dataFragment

    fun initDialog():LiveData<UserModel> =  dataDialog

    fun initStatuUpload():LiveData<StatusDataModel> = dataStatus
    
    fun getDataFragemnt(idjadwal:String?){
        retrofitService.getDetailUserPelatihan(idjadwal).enqueue(object :Callback<UserModel>{
            override fun onFailure(call: Call<UserModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful){
                    dataFragment.postValue(response.body())
                    dataStatus.postValue(StatusDataModel(0))
                }
            }

        })
    }
    
    fun getDataDialog(idjadwal: String?){
        retrofitService.getAllUserPelatihan(idjadwal).enqueue(object : Callback<UserModel> {
            override fun onFailure(call: Call<UserModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful){
                    dataDialog.postValue(response.body())
                }
            }

        })
    }

    fun addUserToJadwal(iduser:String?, idjadwal: String?){
        retrofitService.addUserPelatihan(idjadwal,iduser).enqueue(object : Callback<StatusDataModel>{
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {
                if (response.isSuccessful){
                    dataStatus.postValue(StatusDataModel(1))
                    getDataFragemnt(idjadwal)
                }
            }
        })
    }
    fun deleteUserToJadwal(iduser:String?){
        retrofitService.deleteUserPelatihan(iduser).enqueue(object : Callback<StatusDataModel>{
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {
                if (response.isSuccessful){
                    dataStatus.postValue(StatusDataModel(1))
                }
            }
        })
    }

}