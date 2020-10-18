package com.beone.kevin.ui.pelatih.schedulepelatih

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.DetailJadwalPelatihModelItem
import com.beone.kevin.remote.model.JadwalModel
import com.beone.kevin.remote.model.JadwalPelatihModel
import com.beone.kevin.remote.model.StatusDataModel
import com.beone.kevin.ui.pelatih.DayEnum
import com.beone.kevin.ui.pelatih.SubjectEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val throwable: Throwable) : Resource<T>()
}

class SchedulePelatihViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    private val data: MutableLiveData<JadwalPelatihModel> = MutableLiveData()
    private val data2: MutableLiveData<StatusDataModel> = MutableLiveData()
//    private val _dataJadwal  = MutableLiveData<ArrayList<JadwalModel>>()
//
//    //yg diakses fragment
//    val dataJadwal:LiveData<ArrayList<JadwalModel>> get() = _dataJadwal

    fun initData():LiveData<JadwalPelatihModel> = data

    fun initDialog():LiveData<StatusDataModel> = data2

//    @ExperimentalCoroutinesApi
//    fun getAllDataSchedule(iduser: String?){
//        Log.d(TAG, "getAllDataSchedule: 2")
//        var tempData = ArrayList<JadwalModel>()
//        viewModelScope.launch {
//            getData(iduser).collect {
//                when(it){
//                    is Resource.Success ->{
//                        Log.d(TAG, "getAllDataSchedule:SUCCESS ${it.data.toString()}")
//                        it.data.asFlow().onEach {datas->
//                            getDataDetailJadwal(iduser,datas.id_jadwal).collect{
//                                when(it){
//                                    is Resource.Success ->{
//                                        tempData.add(JadwalModel(
//                                            datas,
//                                            it.data
//                                        ))
//                                        Log.d(TAG, "getAllDataSchedule: datas ${datas.toString()}")
//                                        Log.d(TAG, "getAllDataSchedule: ${JadwalModel(
//                                            datas,
//                                            it.data
//                                        ).toString()}")
//                                    }
//                                    is Resource.Loading ->{
//                                        Log.d(TAG, "getAllDataSchedule: loading")
//                                    }
//                                    is Resource.Failure ->{
//                                        Log.d(TAG, "getAllDataSchedule: failed")
//                                    }
//                                }
//                            }
//                        }.onCompletion { _dataJadwal.postValue(tempData)
//                            for (item in tempData){
//                                Log.d(TAG, "getAllDataSchedule: ${item.toString()}")
//                            }
//                        }.flowOn(Dispatchers.IO).toList()
//                    }
//                    is Resource.Failure ->{
//                        Log.e(TAG, "getAllDataSchedule: ",it.throwable )
//                    }
//                    is Resource.Loading ->{
//                        Log.d(TAG, "getAllDataSchedule: laoding")
//                    }
//                }
//            }
//        }
//    }

//    fun getData(iduser:String?) = flow<Resource<JadwalPelatihModel>>{
//        emit(Resource.Loading())
//        retrofitService.getSchedullerAllPelatih(iduser).apply {
//            emit(Resource.Success(this))
//        }
//    }.catch {e ->
//        emit(Resource.Failure(e))
//    }.flowOn(Dispatchers.IO)

//    @ExperimentalCoroutinesApi
//    fun getDataDetailJadwal(iduser: String?, idjadwal: String?) = flow<Resource<List<DetailJadwalPelatihModelItem>>>{
//        emit(Resource.Loading())
//        val result = retrofitService.getCoachDetailSchedule(idjadwal)
//        emit(Resource.Success(result))
//    }.catch {e->
//        Log.e(TAG, "getDataDetailJadwal: ",e )
//    }.flowOn(Dispatchers.IO)

    fun getData(iduser:String?){
        Log.d(TAG, "getData: ${iduser}")
        retrofitService.getSchedullerAllPelatih(iduser).enqueue(object :
            Callback<JadwalPelatihModel> {
            override fun onFailure(call: Call<JadwalPelatihModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(
                call: Call<JadwalPelatihModel>,
                response: Response<JadwalPelatihModel>
            ) {
                if (response.isSuccessful) {
                    data.postValue(response.body())
                    data2.postValue(StatusDataModel(0))
                }
            }
        })
    }
//
//    fun getDataDetailJadwal(iduser: String?, idjadwal: String?){
//        retrofitService.getCoachDetailSchedule(idjadwal).enqueue(object : Callback<JadwalPelatihModel>{
//            override fun onFailure(call: Call<JadwalPelatihModel>, t: Throwable) {
//                Log.e(TAG, "onFailure: ",t )
//            }
//
//            override fun onResponse(
//                call: Call<JadwalPelatihModel>,
//                response: Response<JadwalPelatihModel>
//            ) {
//                if (response.isSuccessful){
//                    getData(iduser)
//                    data.postValue(response.body())
//                }
//            }
//        })
//    }

    fun deleteData(iduser: String?,idSubject:String?){
        retrofitService.deleteSchedulePelatih(idSubject).enqueue(object : Callback<StatusDataModel>{
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ",t )
            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {
                if (response.isSuccessful ){
                    getData(iduser)
                }
            }

        })
    }

    fun addSchedule(iduser: String?, idsubject: SubjectEnum, tglmulai: String, tglselesai: String){

        retrofitService.addSchedule(iduser,idsubject.subjectdbposition,tglmulai,tglselesai).enqueue(object :
            Callback<StatusDataModel>{
            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ",t )
            }

            override fun onResponse(
                call: Call<StatusDataModel>,
                response: Response<StatusDataModel>
            ) {
                if (response.isSuccessful){
                    data2.postValue(StatusDataModel(1))
                    getData(iduser)
                }else{
                    data2.postValue(StatusDataModel(0))
                }
            }
        })
    }

//    fun addDetailSchedule(idjadwal: String?, hari: DayEnum, tanggal: String, jammulai: String, jamselesai: String){
//
//        retrofitService.addScheduleDetail(idjadwal, hari, tanggal, jammulai, jamselesai).enqueue(object :
//            Callback<StatusDataModel>{
//            override fun onFailure(call: Call<StatusDataModel>, t: Throwable) {
//                Log.e(TAG, "onFailure: ",t )
//            }
//
//            override fun onResponse(
//                call: Call<StatusDataModel>,
//                response: Response<StatusDataModel>
//            ) {
//                if (response.isSuccessful){
//                    data2.postValue(StatusDataModel(1))
//                    getData(idjadwal)
//                }else{
//                    data2.postValue(StatusDataModel(0))
//                }
//            }
//        })
//    }

    companion object {
        private const val TAG = "SchedulePelatihViewModel"
    }
}