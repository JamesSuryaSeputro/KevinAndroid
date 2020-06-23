package com.beone.kevin.remote

import com.beone.kevin.remote.model.*
import com.beone.kevin.ui.pelatih.DayEnum
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitService {

    @POST("loginuser")
    @FormUrlEncoded
    fun checkLoginUser(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<StatusLogin>

    @POST("loginpelatih")
    @FormUrlEncoded
    fun checkLoginPelatih(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<StatusLogin>

    @POST("loginpegawai")
    @FormUrlEncoded
    fun checkLoginPegawai(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<StatusLogin>

    @POST("pembayaranuser")
    @FormUrlEncoded
    fun checkPembayaran(@Field("iduser") iduser: String?): Call<InfoPembayaranModel>

    @POST("checkdocuser")
    @FormUrlEncoded
    fun checkDocUser(@Field("iduser") iduser: String?): Call<CheckDocumentModel>

    @POST("uploadpembayaran")
    @FormUrlEncoded
    fun uploadPembayaran(
        @Field("iduser") iduser: String?,
        @Field("buktipembayaran") buktipembayaran: String?
    ): Call<StatusDataModel>

    @POST("uploaddocuser")
    @FormUrlEncoded
    fun uploadDocUser(
        @Field("iduser") iduser: String?,
        @Field("scanktp") scanKtp: String?,
        @Field("scankompensasi") scanKompensasi: String?,
        @Field("scansuratkesehatan") scanSuratKesehatan: String?,
        @Field("scansuratkerja") scanSuratKerja: String?
    ): Call<StatusDataModel>

    @POST("addjadwal")
    @FormUrlEncoded
    fun addSchedule(
        @Field("iduser") iduser: String?,
        @Field("idsubject") idsubject: Int,
        @Field("hari") hari: DayEnum
    ): Call<StatusDataModel>

    @POST("get_jadwal_pelatihan_all")
    @FormUrlEncoded
    fun getSchedullerAllPelatih(
        @Field("iduser") iduser: String?
    ): Call<JadwalPelatihModel>

    @POST("delete_jadwal_pelatih")
    @FormUrlEncoded
    fun deleteSchedulePelatih(
        @Field("idjadwal") idjadwal: String?
    ): Call<StatusDataModel>
}