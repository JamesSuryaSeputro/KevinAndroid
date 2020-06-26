package com.beone.kevin.remote.model

import com.beone.kevin.ui.registertki.TypeGenderEnum

class UserModel : ArrayList<UserModelItem>()

data class UserModelItem(
    val id:String,
    val id_user:String,
    val jeniskelamin: String,
    val nama: String,
    val passfoto: String,
    val tanggalterdaftar: String,
    val nilai:String
)