package com.beone.kevin.remote.model

import com.beone.kevin.ui.pelatih.DayEnum

class JadwalPelatihModel : ArrayList<JadwalPelatihModelItem>()

data class JadwalPelatihModelItem(
    val hari: DayEnum,
    val id_jadwal: String,
    val id_subject: String,
    val jam_mulai: String,
    val jam_selesai: String,
    val nama_subject: String,
    val tgl_mulai: String,
    val tgl_selesai: String,
    val nama_pelatih: String
){
    override fun toString(): String {
        return  "${nama_subject} ${hari}"
    }
}