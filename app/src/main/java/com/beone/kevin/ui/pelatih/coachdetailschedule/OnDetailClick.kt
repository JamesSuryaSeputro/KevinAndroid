package com.beone.kevin.ui.pelatih.coachdetailschedule

interface OnDetailClick {
    fun onDelete(id:String?)
    fun onDetailScore(idDetailJadwal:String?, idJadwal: String?, hari: String?, tanggal: String?, jamMulai: String?, jamSelesai:String?)
    fun onDetailAtendance(idDetailJadwal: String?, idJadwal: String?, hari: String?, tanggal: String?, jammulai: String?, jamselesai: String?)
}