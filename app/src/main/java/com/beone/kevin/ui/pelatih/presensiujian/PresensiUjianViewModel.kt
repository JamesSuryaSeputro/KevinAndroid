package com.beone.kevin.ui.pelatih.presensiujian

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.*
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresensiUjianViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    //    val data:MutableLiveData<JadwalPelatihModel> = MutableLiveData()
//    val data2:MutableLiveData<UserModel> = MutableLiveData()
    val data3: MutableLiveData<StatusDataModel> = MutableLiveData()
    private val dataPresensi: MutableLiveData<UserModel> = MutableLiveData()
    //val data4:MutableLiveData<StatusDataModel> = MutableLiveData()
    //var idPresensi = MutableLiveData<String?>()

    //    fun initSpinner():LiveData<JadwalPelatihModel> = data
//    fun initRecycler():LiveData<UserModel> = data2
    fun initPresensi(): LiveData<StatusDataModel> = data3
    fun initData(): LiveData<UserModel> = dataPresensi
//    fun initStatus(): LiveData<StatusPresensiModel> = dataStatus
    //fun initCheck():LiveData<StatusDataModel> = data4
    //fun initStatus():LiveData<StatusDataModel> = data3

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
//
//    fun getUser(idjadwal: String?){
//        retrofitService.getAllUserPelatihan(idjadwal).enqueue(object : Callback<UserModel> {
//            override fun onFailure(call: Call<UserModel>, t: Throwable) {
//            }
//
//            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
//                if (response.isSuccessful) {
//                    data2.postValue(response.body())
//                }
//            }
//
//        })
//    }

    fun getDataFragmentPresensi(idjadwal:String?, idjadwaldetail: String?){
        retrofitService.getDetailUserPelatihanPresensi(idjadwal, idjadwaldetail).enqueue(object :Callback<UserModel>{
            override fun onFailure(call: Call<UserModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful){
                    dataPresensi.postValue(response.body())
                }
            }

        })
    }

    fun addPresensiUjian(iddetailjadwal: String?, iduser: String?, statuspresensi: String?) {
        retrofitService.addPresensiUjian(iddetailjadwal, iduser, statuspresensi).enqueue(
            object : Callback<StatusDataModel> {
                override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<StatusDataModel>,
                    response: Response<StatusDataModel>
                ) {
                    data3.postValue(response.body())
                }
            })
    }

    fun updatePresensiUjian(iddetailjadwal: String?, iduser: String?, statuspresensi: String?) {
        retrofitService.updatePresensiUjian(iddetailjadwal, iduser, statuspresensi).enqueue(
            object : Callback<StatusDataModel> {
                override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<StatusDataModel>,
                    response: Response<StatusDataModel>
                ) {
                    data3.postValue(response.body())
                }
            })
    }

    fun checkPresensi(iddetailjadwal: String?, iduser: String?, statuspresensi: String?) {
        retrofitService.checkPresensi(iddetailjadwal, iduser).enqueue(
            object : Callback<StatusDataModel> {
                override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<StatusDataModel>,
                    response: Response<StatusDataModel>
                ) {
                    if (response.body()!!.status.equals(0)) {
                        addPresensiUjian(iddetailjadwal, iduser, statuspresensi)
                        // data3.postValue(response.body())
                    } else {
                        updatePresensiUjian(iddetailjadwal, iduser, statuspresensi)
                    }
                }
            })
    }
}