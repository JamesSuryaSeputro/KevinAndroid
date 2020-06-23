package com.beone.kevin.remote

import com.beone.kevin.remote.model.*
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

    @POST("registertki")
    @FormUrlEncoded
    fun registerTki(@Field("username") username: String?,
                    @Field("password") password: String?,
                    @Field("nopassport") nopassport: String?,
                    @Field("noktp") noktp: String?,
                    @Field("tempatlahir") tempatlahir: String?,
                    @Field("tanggallahir") tanggallahir: String?,
                    @Field("umur") umur:String?,
                    @Field("kewarganegaraan") kewarganegaraan:String?,
                    @Field("jeniskelamin") jeniskelamin:String?,
                    @Field("alamat") alamat:String?,
                    @Field("notelpalternative") notelpalternative:String?,
                    @Field("statuspernikahan") statuspernikahan:String?,
                    @Field("tinggibadan") tinggibadan:String?,
                    @Field("beratbadan") beratbadan:String?,
                    @Field("matakiri") matakiri:String?,
                    @Field("matakanan") matakanan:String?,
                    @Field("butawarna") butawarna:String?,
                    @Field("upahyangdiinginkan") upahyangdiinginkan:String?,
                    @Field("sektor1") sektor1:String?,
                    @Field("sektor2") sektor2:String?,
                    @Field("sektor3") sektor3:String?,
                    @Field("pekerjaan1") pekerjaan1:String?,
                    @Field("pekerjaan2") pekerjaan2:String?,
                    @Field("pekerjaan3") pekerjaan3:String?,
                    @Field("lokasi1") lokasi1:String?,
                    @Field("lokasi2") lokasi2:String?,
                    @Field("lokasi3") lokasi3:String?,
                    @Field("pendidikanterakhir") pendidikanterakhir:String?,
                    @Field("bidang") bidang:String?,
                    @Field("mandarin") mandarin:String?,
                    @Field("inggris") inggris:String?,
                    @Field("bahasalain") bahasalain:String?,
                    @Field("sertifikatkerja1") sertifikatkerja1:String?,
                    @Field("sertifikatkerja2") sertifikatkerja2:String?,
                    @Field("sertifikatkerja3") sertifikatkerja3:String?,
                    @Field("pengalamankerja1") pengalamankerja1:String?,
                    @Field("pengalamankerjatanggalmulai1") pengalamankerjatanggalmulai1:String?,
                    @Field("pengalamankerjaselesai1") pengalamankerjaselesai1:String?,
                    @Field("pengalamankerja2") pengalamankerja2:String?,
                    @Field("pengalamankerjatanggalmulai2") pengalamankerjatanggalmulai2:String?,
                    @Field("pengalamankerjaselesai2") pengalamankerjaselesai2:String?,
                    @Field("hasilmedicalcheckup") hasilmedicalcheckup:String?,
                    @Field("tanggalmedicalcheck") tanggalmedicalcheck:String?,
                    @Field("klinikmedicalcheck") klinikmedicalcheck:String?,
                    @Field("pendidikanbahasamandarin") pendidikanbahasamandarin:String?,
                    @Field("namalembagapendidikan") namalembagapendidikan:String?,
                    @Field("tanggalpendidikanbahasamandarin") tanggalpendidikanbahasamandarin:String?,
                    @Field("passfoto") passfoto:String?,
                    @Field("ttdfoto") ttdfoto:String?): Call<StatusDataModel>
}