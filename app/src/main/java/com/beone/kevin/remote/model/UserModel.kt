package com.beone.kevin.remote.model

class UserModel : ArrayList<UserModelItem>()

data class UserModelItem(
    val id:String,
    val id_user:String,
    val jeniskelamin: String,
    val nama: String,
    val passfoto: String,
    val tanggalterdaftar: String,
    val nilai:String,
    val status: String,
    val status_presensi: Int
)