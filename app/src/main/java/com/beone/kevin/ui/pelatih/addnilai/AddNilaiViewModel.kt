package com.beone.kevin.ui.pelatih.addnilai

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

class AddNilaiViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    private val data: MutableLiveData<JadwalPelatihModel> = MutableLiveData()
    private val dataStatus: MutableLiveData<StatusDataModel> = MutableLiveData()
    private val datausernilai:MutableLiveData<UserModel> = MutableLiveData()

    fun initFragment(): LiveData<JadwalPelatihModel> = data

    fun initStatus(): LiveData<StatusDataModel> = dataStatus
    fun initFragment2(): LiveData<UserModel> = datausernilai

    fun getDataJadwal(iduser: String?) {
        retrofitService.getSchedullerAllPelatih(iduser).enqueue(object :
            Callback<JadwalPelatihModel> {
            override fun onFailure(call: Call<JadwalPelatihModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<JadwalPelatihModel>,
                response: Response<JadwalPelatihModel>
            ) {
                if (response.isSuccessful) {
                    data.postValue(response.body())
                    dataStatus.postValue(StatusDataModel(0))
                }
            }
        })
    }

    fun getDataSiswa(idDetailJadwal: String?){
        retrofitService.getDetailUserNilai(idDetailJadwal).enqueue(object : Callback<UserModel>{
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful){
                    datausernilai.postValue(response.body())
                    dataStatus.postValue(StatusDataModel(0))
                }
            }

        })
    }

//    fun addNilaiSiswa(idUser:String?,nilai:String?,idJadwal: String?){
//        retrofitService.addNilaiUser(idUser,nilai).enqueue(object : Callback<StatusDataModel>{
//            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
//            }
//
//            override fun onResponse(
//                call: Call<StatusDataModel>,
//                response: Response<StatusDataModel>
//            ) {
//                if (response.isSuccessful){
//                    dataStatus.postValue(StatusDataModel(1))
//                    getDataSiswa(idJadwal)
//                }
//            }
//
//        })
//    }

}