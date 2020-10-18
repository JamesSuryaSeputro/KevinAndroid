package com.beone.kevin.remote.model

class JadwalPelatihModel : ArrayList<JadwalPelatihModelItem>()

data class JadwalPelatihModelItem(
    val id_jadwal: String,
    val id_subject: String,
    val nama_pelatih: String,
    val nama_subject: String,
    val tgl_mulai: String,
    val tgl_selesai: String
)

//data class DetailJadwalPelatihModelItem(
//    val id_jadwal_detail:String,
//    val id_jadwal: String,
//    val hari:String,
//    val tanggal:String,
//    val jam_mulai:String,
//    val jam_selesai:String
//)
//
//data class JadwalModel(
//    val jadwalheader:JadwalPelatihModelItem,
//    val jadwaldetail:List<DetailJadwalPelatihModelItem> = ArrayList()
//)
