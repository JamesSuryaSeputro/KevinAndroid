package com.beone.kevin.remote.model

class DetailJadwalPelatihModel : ArrayList<DetailJadwalPelatihModelItem>()

data class DetailJadwalPelatihModelItem(
    val id_jadwal_detail:String,
    val id_jadwal: String,
    val hari:String,
    val tanggal:String,
    val jam_mulai:String,
    val jam_selesai:String,
    val id_presensi:String,
    val id_user: String,
    val status_presensi: Int,
    val nilai: String,
    val datecreated: String
)
