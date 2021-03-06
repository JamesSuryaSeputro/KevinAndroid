package com.beone.kevin.ui.registertki

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.CheckUserDataModel
import com.beone.kevin.remote.model.RegisterTKIModel
import com.beone.kevin.remote.model.StatusDataModel
import com.beone.kevin.ui.hrd.checkuserpayment.CheckPembayaranViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterTkiViewModel(val retrofitService: RetrofitService) : ViewModel() {

    companion object {
        private const val TAG = "RegisterTkiViewModel"
    }

    private var data : MutableLiveData<StatusDataModel> = MutableLiveData()
    fun initDataRegisterTki():LiveData<StatusDataModel>{
        return data
    }

    private var data2 : MutableLiveData<RegisterTKIModel> = MutableLiveData()
    fun initDataProfileUser():LiveData<RegisterTKIModel>{
        return data2
    }

    fun registerTki(a:RegisterTKIModel? ){
            retrofitService.registerTki(a?.username, a?.password, a?.nama, a?.nopassport, a?.noktp, a?.tempatlahir,
                a?.tanggallahir, a?.umur, a?.kewarganegaraan, a?.jeniskelamin, a?.alamat, a?.notelp, a?.notelpalternative,
                a?.statuspernikahan, a?.tinggibadan, a?.beratbadan, a?.matakiri, a?.matakanan, a?.butawarna,
                a?.upahyangdiinginkan, a?.sektor1, a?.sektor2, a?.sektor3, a?.pekerjaan1, a?.pekerjaan2,
                a?.pekerjaan3, a?.lokasi1, a?.lokasi2, a?.lokasi3, a?.pendidikanterakhir, a?.bidang, a?.mandarin,
                a?.inggris, a?.bahasalain, a?.sertifikatkerja1, a?.sertifikatkerja2, a?.sertifikatkerja3,
                a?.pengalamankerja1, a?.pengalamankerjatanggalmulai1, a?.pengalamankerjaselesai1, a?.pengalamankerja2,
                a?.pengalamankerjatanggalmulai2, a?.pengalamankerjaselesai2, a?.hasilmedicalcheckup, a?.tanggalmedicalcheck,
                a?.klinikmedicalcheck, a?.pendidikanbahasamandarin, a?.namalembagapendidikan, a?.tglmulaipendidikanmandarin,
                a?.tglselesaipendidikanmandarin, a?.passfoto, a?.ttdfoto).enqueue(object : Callback<StatusDataModel> {
                override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                    Log.e(TAG, "onFailure: ",t )
                }

                override fun onResponse(
                    call: Call<StatusDataModel>,
                    response: Response<StatusDataModel>
                ) {
                    if (response.isSuccessful && response.body()!= null){
                        data.postValue(response.body())
                    }
                }
            })
        }

    fun showProfileUser(a:RegisterTKIModel? ){
        retrofitService.getProfilUser(a?.username, a?.password, a?.nama, a?.nopassport, a?.noktp, a?.tempatlahir,
            a?.tanggallahir, a?.umur, a?.kewarganegaraan, a?.jeniskelamin, a?.alamat, a?.notelp, a?.notelpalternative,
            a?.statuspernikahan, a?.tinggibadan, a?.beratbadan, a?.matakiri, a?.matakanan, a?.butawarna,
            a?.upahyangdiinginkan, a?.sektor1, a?.sektor2, a?.sektor3, a?.pekerjaan1, a?.pekerjaan2,
            a?.pekerjaan3, a?.lokasi1, a?.lokasi2, a?.lokasi3, a?.pendidikanterakhir, a?.bidang, a?.mandarin,
            a?.inggris, a?.bahasalain, a?.sertifikatkerja1, a?.sertifikatkerja2, a?.sertifikatkerja3,
            a?.pengalamankerja1, a?.pengalamankerjatanggalmulai1, a?.pengalamankerjaselesai1, a?.pengalamankerja2,
            a?.pengalamankerjatanggalmulai2, a?.pengalamankerjaselesai2, a?.hasilmedicalcheckup, a?.tanggalmedicalcheck,
            a?.klinikmedicalcheck, a?.pendidikanbahasamandarin, a?.namalembagapendidikan, a?.tglmulaipendidikanmandarin,
            a?.tglselesaipendidikanmandarin, a?.passfoto, a?.ttdfoto).enqueue(object :
            Callback<RegisterTKIModel> {
            override fun onFailure(call: Call<RegisterTKIModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ",t )
            }

            override fun onResponse(
                call: Call<RegisterTKIModel>,
                response: Response<RegisterTKIModel>
            ) {
                if (response.isSuccessful && response.body()!= null){
                    data2.postValue(response.body())
                }
            }
        })
    }
}