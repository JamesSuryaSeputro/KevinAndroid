package com.beone.kevin.remote

import com.beone.kevin.remote.model.CheckDocumentModel
import com.beone.kevin.remote.model.InfoPembayaranModel
import com.beone.kevin.remote.model.StatusDataModel
import com.beone.kevin.remote.model.StatusLogin
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
    fun uploadPembayaran(@Field("iduser") iduser:String?,
    @Field("buktipembayaran")buktipembayaran:String?): Call<StatusDataModel>

}