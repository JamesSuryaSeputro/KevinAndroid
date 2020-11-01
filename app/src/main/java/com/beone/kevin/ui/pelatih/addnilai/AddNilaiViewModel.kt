package com.beone.kevin.ui.pelatih.addnilai

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.StatusDataModel
import com.beone.kevin.remote.model.StatusDataModel2
import com.beone.kevin.remote.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class AddNilaiViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    //    private val data: MutableLiveData<JadwalPelatihModel> = MutableLiveData()
    private val dataStatus: MutableLiveData<StatusDataModel> = MutableLiveData()
    private val dataUserNilai: MutableLiveData<UserModel> = MutableLiveData()

//    fun initFragment(): LiveData<JadwalPelatihModel> = data

    fun initStatus(): LiveData<StatusDataModel> = dataStatus
    fun initNilai(): LiveData<UserModel> = dataUserNilai

    fun getDataNilaiSiswa(idjadwal: String?, idjadwaldetail: String?) {
        retrofitService.getDetailUserPelatihanNilai(idjadwal, idjadwaldetail)
            .enqueue(object : Callback<UserModel> {
                override fun onFailure(call: Call<UserModel>, t: Throwable) {

                }

                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    if (response.isSuccessful) {
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

    fun checkNilai(
        idpelatihan: String?,
        idjadwaldetail: String?,
        nilai: String?,
        idjadwal: String?,
        iduser: String?
    ) {
        retrofitService.checkNilai(idpelatihan, idjadwaldetail).enqueue(
            object : Callback<StatusDataModel2> {
                override fun onFailure(call: Call<StatusDataModel2>, t: Throwable) {
                    Log.e("Hei Hei®", "onFailure: ", t)
                }

                override fun onResponse(
                    call: Call<StatusDataModel2>,
                    response: Response<StatusDataModel2>
                ) {
                    checkScoreAverage(idjadwal, iduser)
                    response.body()?.apply {
                        Log.d("HEHEHE", "onResponse: ${this}")
                        if (this.status.equals("0")) {
                            addNilai(idpelatihan, idjadwaldetail, nilai)
                            // data3.postValue(response.body())
                        } else if (this.status.equals("1")) {
                            updateNilai(idpelatihan, idjadwaldetail, nilai)
                        }

                    }
                }
            })
    }


    fun addScoreAverage(idjadwal: String?) {
        retrofitService.addScoreAverage(idjadwal).enqueue(
            object : Callback<StatusDataModel> {
                override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                    Log.e("Hei Hei", "onFailure: ", t)
                }

                override fun onResponse(
                    call: Call<StatusDataModel>,
                    response: Response<StatusDataModel>
                ) {
                    dataStatus.postValue(response.body())
                }
            })
    }

    fun updateScoreAverage(idjadwal: String?, iduser: String?) {
        retrofitService.updateScoreAverage(idjadwal, iduser).enqueue(
            object : Callback<StatusDataModel> {
                override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                    Log.e("hei hei", "onFailure: ", t)
                }

                override fun onResponse(
                    call: Call<StatusDataModel>,
                    response: Response<StatusDataModel>
                ) {
                    dataStatus.postValue(response.body())
                }
            })
    }

    fun checkScoreAverage(idjadwal: String?, iduser: String?) {
        retrofitService.checkScoreAverage(idjadwal, iduser).enqueue(
            object : Callback<StatusDataModel2> {
                override fun onFailure(call: Call<StatusDataModel2>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<StatusDataModel2>,
                    response: Response<StatusDataModel2>
                ) {
                    response.body()?.apply {
                        Log.d("HEHEHE", "onResponse: ${this}")
                        if (this.status.equals("0")) {
                            addScoreAverage(idjadwal)
                            // data3.postValue(response.body())
                        } else if (this.status.equals("1")) {
                            updateScoreAverage(idjadwal, iduser)
                        }

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
//                   // getDataSiswa(idJadwal)
//                }
//            }
//
//        })
//    }

}