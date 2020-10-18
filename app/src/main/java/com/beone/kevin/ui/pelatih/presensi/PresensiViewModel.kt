package com.beone.kevin.ui.pelatih.presensi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.JadwalPelatihModel
import com.beone.kevin.remote.model.StatusDataModel
import com.beone.kevin.remote.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresensiViewModel(private val retrofitService: RetrofitService) : ViewModel() {
    val data: MutableLiveData<JadwalPelatihModel> = MutableLiveData()
    val data2: MutableLiveData<UserModel> = MutableLiveData()
    fun initSpinner(): LiveData<JadwalPelatihModel> = data
    fun initRecycler(): LiveData<UserModel> = data2

//    fun getdatajadwal(iduser:String?){
//        retrofitService.getSchedullerAllPelatih(iduser).enqueue(object :
//            Callback<JadwalPelatihModel> {
//            override fun onFailure(call: Call<JadwalPelatihModel>, t: Throwable) {
//
//            }
//
//            override fun onResponse(
//                call: Call<JadwalPelatihModel>,
//                response: Response<JadwalPelatihModel>
//            ) {
//                if (response.isSuccessful){
//                    data.postValue(response.body())
//                }
//            }
//
//        })
//    }

    fun getUser(idjadwal: String?){
        retrofitService.getAllUserPelatihan(idjadwal).enqueue(object : Callback<UserModel> {
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful){
                    data2.postValue(response.body())
                }
            }

        })
    }

    fun addPresensiTest(idjadwal: String?,iduser: String?){
        retrofitService.addPresensiTest(idjadwal,iduser).enqueue(object :
            Callback<StatusDataModel> {
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {

            }
        })
    }
}