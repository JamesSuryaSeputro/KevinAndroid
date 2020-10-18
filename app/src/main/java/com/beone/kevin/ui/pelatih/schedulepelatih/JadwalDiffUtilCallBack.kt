package com.beone.kevin.ui.pelatih.schedulepelatih

import androidx.recyclerview.widget.DiffUtil
import com.beone.kevin.remote.model.JadwalModel
import com.beone.kevin.remote.model.JadwalPelatihModel

class JadwalDiffUtilCallBack(val oldData: ArrayList<JadwalModel>, val newData: ArrayList<JadwalModel>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.get(oldItemPosition).jadwalheader.id_jadwal == newData.get(newItemPosition).jadwalheader.id_jadwal
    }

    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.get(oldItemPosition).equals(newData.get(newItemPosition))
    }

}