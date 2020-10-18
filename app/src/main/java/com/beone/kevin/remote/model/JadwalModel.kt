package com.beone.kevin.remote.model

data class JadwalModel(
    val jadwalheader:JadwalPelatihModelItem,
    val jadwaldetail:List<DetailJadwalPelatihModelItem> = ArrayList()
)