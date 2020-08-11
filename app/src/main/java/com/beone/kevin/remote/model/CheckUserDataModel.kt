package com.beone.kevin.remote.model

class CheckUserDataModel : ArrayList<CheckUserDataModelItem>()

data class CheckUserDataModelItem(
    val datecreated: String,
    val iduser: String,
    val nama: String

)