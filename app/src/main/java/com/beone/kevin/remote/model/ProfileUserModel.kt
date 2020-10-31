package com.beone.kevin.remote.model

class ShippingUserModel : ArrayList<ProfileUserModel>()

data class ProfileUserModel(
    val alamat: String,
    val jeniskelamin: String,
    val kewarganegaraan: String,
    val nama: String,
    val no_ktp: String,
    val no_passport: String,
    val notelp: String,
    val passfoto: String,
    val tanggallahir: String,
    val tempatlahir: String,
    val username: String,
    val year: String,
    val id_user: String,
    val status: Int,
    val id: String
)