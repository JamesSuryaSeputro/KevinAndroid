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

//    private val data: MutableLiveData<JadwalPelatihModel> = MutableLiveData()
    private val dataStatus: MutableLiveData<StatusDataModel> = MutableLiveData()
    private val dataUserNilai:MutableLiveData<UserModel> = MutableLiveData()

//    fun initFragment(): LiveData<JadwalPelatihModel> = data

    fun initStatus(): LiveData<StatusDataModel> = dataStatus
    fun initNilai(): LiveData<UserModel> = dataUserNilai

//    fun getDataJadwal(iduser: String?) {
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
//                if (response.isSuccessful) {
//                    data.postValue(response.body())
//                    dataStatus.postValue(StatusDataModel(0))
//                }
//            }
//        })
//    }

//    fun getDataSiswa(idDetailJadwal: String?){
//        retrofitService.getDetailUserNilai(idDetailJadwal).enqueue(object : Callback<UserModel>{
//            override fun onFailure(call: Call<UserModel>, t: Throwable) {
//            }
//
//            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
//                if (response.isSuccessful){
//                    datausernilai.postValue(response.body())
//                    dataStatus.postValue(StatusDataModel(0))
//                }
//            }
//
//        })
//    }

    fun getDataNilaiSiswa(idjadwal:String?, idjadwaldetail: String?){
        retrofitService.getDetailUserPelatihanNilai(idjadwal, idjadwaldetail).enqueue(object :Callback<UserModel>{
            override fun onFailure(call: Call<UserModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful){
                    dataUserNilai.postValue(response.body())
                }
            }

        })
    }

    fun addNilai(idpelatihan: String?, idjadwaldetail: String?, nilai: String?) {
        retrofitService.addNilai(idpelatihan, idjadwaldetail, nilai).enqueue(
            object : Callback<StatusDataModel> {
                override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<StatusDataModel>,
                    response: Response<StatusDataModel>
                ) {
                    dataStatus.postValue(response.body())
                }
            })
    }

    fun updateNilai(idpelatihan: String?, idjadwaldetail: String?, nilai: String?) {
        retrofitService.updateNilai(idpelatihan, idjadwaldetail, nilai).enqueue(
            object : Callback<StatusDataModel> {
                override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<StatusDataModel>,
                    response: Response<StatusDataModel>
                ) {
                    dataStatus.postValue(response.body())
                }
            })
    }

    fun checkNilai(idpelatihan: String?, idjadwaldetail: String?, nilai: String?) {
        retrofitService.checkNilai(idpelatihan, idjadwaldetail).enqueue(
            object : Callback<StatusDataModel> {
                override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<StatusDataModel>,
                    response: Response<StatusDataModel>
                ) {
                    if (response.body()!!.status.equals(0)) {
                        addNilai(idpelatihan, idjadwaldetail, nilai)
                        // data3.postValue(response.body())
                    } else {
                        updateNilai(idpelatihan, idjadwaldetail, nilai)
                    }
                }
            })
    }

    fun addNilaiSiswa(idUser:String?,nilai:String?,idJadwal: String?){
        retrofitService.addNilaiUser(idUser,nilai).enqueue(object : Callback<StatusDataModel>{
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {
                if (response.isSuccessful){
                    dataStatus.postValue(StatusDataModel(1))
                   // getDataSiswa(idJadwal)
                }
            }

        })
    }

}