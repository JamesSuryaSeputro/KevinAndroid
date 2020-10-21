package com.beone.kevin.remote


import com.beone.kevin.remote.model.*
import com.beone.kevin.ui.pelatih.DayEnum
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
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
        @Field("tglmulai") tglmulai: String?,
        @Field("tglselesai") tglselesai: String?
    ): Call<StatusDataModel>

    @POST("adddetailjadwal")
    @FormUrlEncoded
    fun addScheduleDetail(
        @Field("idjadwal") idjadwal: String?,
        @Field("hari") hari: DayEnum,
        @Field("tanggal") tanggal: String?,
        @Field("jammulai") jammulai: String?,
        @Field("jamselesai") jamselesai: String?
    ): Call<StatusDataModel>

    @POST("get_jadwal_pelatihan_all")
    @FormUrlEncoded
    fun getSchedullerAllPelatih(
        @Field("iduser") iduser: String?
    ): Call<JadwalPelatihModel>

    @POST("get_detail_jadwal_pelatihan")
    @FormUrlEncoded
    fun getCoachDetailSchedule(
        @Field("idjadwal") idjadwal: String?
    ): Call<DetailJadwalPelatihModel>

//    @POST("get_jadwal_pelatihan_all")
//    @FormUrlEncoded
//    suspend fun getSchedullerAllPelatih(
//        @Field("iduser") iduser: String?
//    ): JadwalPelatihModel
//
//    @POST("get_detail_jadwal_pelatihan")
//    @FormUrlEncoded
//    suspend fun getCoachDetailSchedule(
//        @Field("idjadwal") idjadwal: String?
//    ): List<DetailJadwalPelatihModelItem>

    @POST("get_jadwal_pelatihan_user")
    @FormUrlEncoded
    suspend fun getScheduleUser(
        @Field("iduser") iduser: String?
    ): JadwalPelatihModel

    @POST("get_detail_jadwal_pelatihan")
    @FormUrlEncoded
    suspend fun getDetailScheduleUser(
        @Field("idjadwal") idjadwal: String?
    ): List<DetailJadwalPelatihModelItem>

    @POST("registertki")
    @FormUrlEncoded
    fun registerTki(
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("nama") nama: String?,
        @Field("no_passport") nopassport: String?,
        @Field("no_ktp") noktp: String?,
        @Field("tempatlahir") tempatlahir: String?,
        @Field("tanggallahir") tanggallahir: String?,
        @Field("kewarganegaraan") kewarganegaraan: String?,
        @Field("jeniskelamin") jeniskelamin: String?,
        @Field("alamat") alamat: String?,
        @Field("notelp") notelp: String?,
        @Field("passfoto") passfoto: String?,
        @Field("ttdfoto") ttdfoto: String?
    ): Call<StatusDataModel>

    @POST("registerpelatih")
    @FormUrlEncoded
    fun registerPelatih(
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("nama_pelatih") namapelatih: String?
    ): Call<StatusDataModel>

    @POST("registerpegawai")
    @FormUrlEncoded
    fun registerPegawai(
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("nama_pegawai") namapegawai: String?,
        @Field("nip") nip: String?
    ): Call<StatusDataModel>

    @POST("delete_jadwal_pelatih")
    @FormUrlEncoded
    fun deleteSchedulePelatih(
        @Field("idjadwal") idjadwal: String?
    ): Call<StatusDataModel>

    @POST("delete_detail_jadwal_pelatih")
    @FormUrlEncoded
    fun deleteCoachDetailSchedule(
        @Field("idjadwaldetail") idjadwaldetail: String?
    ): Call<StatusDataModel>

    @POST("get_user_all")
    @FormUrlEncoded
    fun getAllUserPelatihan(
        @Field("idjadwal") idjadwal: String?
    ): Call<UserModel>

    @POST("get_detail_user_pelatihan")
    @FormUrlEncoded
    fun getDetailUserPelatihan(@Field("idjadwal") idjadwal: String?): Call<UserModel>

    @POST("add_user_pelatihan")
    @FormUrlEncoded
    fun addUserPelatihan(
        @Field("idjadwal") idjadwal: String?,
        @Field("iduser") iduser: String?
    ): Call<StatusDataModel>

    @POST("delete_user_jadwal")
    @FormUrlEncoded
    fun deleteUserPelatihan(
        @Field("idpelatihan") idjadwal: String?
    ): Call<StatusDataModel>

    @POST("get_detail_user_nilai")
    @FormUrlEncoded
    fun getDetailUserNilai(@Field("idjadwal") idjadwal: String?): Call<UserModel>

    @POST("add_tabel_nilai")
    @FormUrlEncoded
    fun addNilaiUser(
        @Field("idpelatihan") idPelatihan: String?,
        @Field("nilai") nilai: String?
    ): Call<StatusDataModel>

    @POST("add_presensi_ujian")
    @FormUrlEncoded
    fun addPresensiUjian(
        @Field("idjadwal") idjadwal: String?,
        @Field("iduser") iduser: String?
    ): Call<StatusDataModel>

    @POST("add_presensi_test")
    @FormUrlEncoded
    fun addPresensiTest(
        @Field("idjadwal") idjadwal: String?,
        @Field("iduser") iduser: String?
    ): Call<StatusDataModel>

    @POST("profile_user")
    @FormUrlEncoded
    fun getProfileUser(@Field("id_user") iduser: String?): Call<ProfileUserModel>

    @POST("profile_pelatih")
    @FormUrlEncoded
    fun getProfilePelatih(@Field("id_pelatih") idpelatih: String?): Call<ProfilePelatihModel>

    @POST("update_foto_pelatih")
    @FormUrlEncoded
    fun updateFotoPelatih(
        @Field("id_pelatih") idpelatih: String?,
        @Field("foto") foto: String?
    ): Call<StatusDataModel>

    @POST("profile_pegawai")
    @FormUrlEncoded
    fun getProfilePegawai(@Field("id_pegawai") idpegawai: String?): Call<ProfilePegawaiModel>

    @POST("update_foto_pegawai")
    @FormUrlEncoded
    fun updateFotoPegawai(
        @Field("id_pegawai") idpegawai: String?,
        @Field("foto") foto: String?
    ): Call<StatusDataModel>

    @GET("getuserdata")
    fun getUserData(): Call<CheckUserDataModel>

    @POST("getuserdocument")
    @FormUrlEncoded
    fun getUserDocument(@Field("iduser") iduser: String?): Call<CheckDocumentModel>

    @POST("approveuserdocument")
    @FormUrlEncoded
    fun approveUserDocument(
        @Field("iduser") iduser: String?,
        @Field("idpegawai") idpegawai: String?,
        @Field("status") status: String?
    ): Call<StatusDataModel>

    @GET("getuserpaymentdata")
    fun getUserPaymentData(): Call<CheckUserDataModel>

    @POST("getuserpayment")
    @FormUrlEncoded
    fun getUserPayment(@Field("iduser") iduser: String?): Call<InfoPembayaranModel>

    @POST("approveuserpayment")
    @FormUrlEncoded
    fun approveUserPayment(
        @Field("iduser") iduser: String?,
        @Field("idpegawai") idpegawai: String?,
        @Field("status") status: String?
    ): Call<StatusDataModel>
}