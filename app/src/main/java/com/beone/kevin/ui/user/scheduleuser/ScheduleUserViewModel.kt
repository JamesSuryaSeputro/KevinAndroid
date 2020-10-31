package com.beone.kevin.ui.user.scheduleuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.remote.model.*
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

class ScheduleUserViewModel(val retrofitService: RetrofitService) : ViewModel() {

    companion object {
        private const val TAG = "ScheduleUserViewModel"
    }

    private val _dataJadwal = MutableLiveData<ArrayList<JadwalModel>>()

    //yg diakses fragment
    val dataJadwal:LiveData<ArrayList<JadwalModel>> get() = _dataJadwal

    @ExperimentalCoroutinesApi
    fun getAllDataSchedule(iduser: String?) {
        Log.d(TAG, "getAllDataSchedule: 2")
        var tempData = ArrayList<JadwalModel>()
        viewModelScope.launch {
            getData(iduser).collect {
                when (it) {
                    is Resource.Success -> {
                        Log.d(TAG, "getAllDataSchedule:SUCCESS ${it.data.toString()}")
                        it.data.asFlow().onEach { datas ->
                            getDataDetailJadwal(datas.id_jadwal, datas.id_user).collect {
                                when (it) {
                                    is Resource.Success -> {
                                        var counter = 0
                                        var data = datas
                                        var dataSize = it.data.size
                                        it.data.asFlow().onStart {
                                            counter = 0
                                        }.onEach {
                                            if (it.status_presensi.equals(1)) {
                                                counter++
                                                Log.d(TAG, "getAllDataSchedule: COUNTER RUNNING")
                                            }
                                        }.onCompletion {
                                            if (dataSize > 0) {
                                                data.presentaseAbsensi = (counter.toFloat().div(dataSize)*100).toInt()
                                                Log.d(
                                                    TAG,
                                                    "getAllDataSchedule: PERSEN ${data.presentaseAbsensi} \n counter ${counter} \n datasize ${dataSize}"
                                                )
                                            }
                                        }.collect {
                                            Log.d(
                                                TAG,
                                                "getAllDataSchedule: Loading"
                                            )
                                        }
                                        tempData.add(
                                            JadwalModel(
                                                data,
                                                it.data
                                            )
                                        )
                                        Log.d(TAG, "getAllDataSchedule: counter ${counter}")
                                        Log.d(TAG, "getAllDataSchedule: datas ${data.toString()}")
                                        Log.d(
                                            TAG, "getAllDataSchedule: ${
                                                JadwalModel(
                                                    datas,
                                                    it.data
                                                ).toString()
                                            }"
                                        )
                                    }
                                    is Resource.Loading -> {
                                        Log.d(TAG, "getAllDataSchedule: loading")
                                    }
                                    is Resource.Failure -> {
                                        Log.d(TAG, "getAllDataSchedule: failed")
                                    }
                                }
                            }
                        }.onCompletion {
                            _dataJadwal.postValue(tempData)
                            for (item in tempData) {
                                Log.d(TAG, "getAllDataSchedule: ${item.toString()}")
                            }
                        }.flowOn(Dispatchers.IO).toList()
                    }
                    is Resource.Failure -> {
                        Log.e(TAG, "getAllDataSchedule: ", it.throwable)
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "getAllDataSchedule: laoding")
                    }
                }
            }
        }
    }

    fun getData(iduser: String?) = flow<Resource<JadwalPelatihModel>> {
        emit(Resource.Loading())
        retrofitService.getScheduleUser(iduser).apply {
            emit(Resource.Success(this))
        }
    }.catch { e ->
        emit(Resource.Failure(e))
    }.flowOn(Dispatchers.IO)

    @ExperimentalCoroutinesApi
    fun getDataDetailJadwal(idjadwal: String?, iduser: String?) =
        flow<Resource<List<DetailJadwalPelatihModelItem>>> {
            emit(Resource.Loading())
            val result = retrofitService.getDetailScheduleUser(idjadwal, iduser)
            emit(Resource.Success(result))
        }.catch { e ->
            Log.e(TAG, "getDataDetailJadwal: ", e)
        }.flowOn(Dispatchers.IO)

//    fun getDataScheduleUser(iduser: String?) {
//        Log.d(TAG, "getData: ${iduser}")
//        retrofitService.getScheduleUser(iduser).enqueue(object :
//            Callback<JadwalPelatihModel> {
//            override fun onFailure(call: Call<JadwalPelatihModel>, t: Throwable) {
//                Log.e(TAG, "onFailure: ", t)
//            }
//
//            override fun onResponse(
//                call: Call<JadwalPelatihModel>,
//                response: Response<JadwalPelatihModel>
//            ) {
//                if (response.isSuccessful) {
//                    data.postValue(response.body())
//                    data2.postValue(StatusDataModel(0))
//                }
//            }
//        })
//    }
}