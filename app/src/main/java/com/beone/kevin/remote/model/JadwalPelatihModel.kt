package com.beone.kevin.remote.model

import com.beone.kevin.ui.pelatih.DayEnum

class JadwalPelatihModel : ArrayList<JadwalPelatihModelItem>()

data class JadwalPelatihModelItem(
    val hari: DayEnum,
    val id_jadwal: String,
    val id_subject: String,
    val nama_subject: String
){
    override fun toString(): String {
        return  "${nama_subject} ${hari}"
    }
}