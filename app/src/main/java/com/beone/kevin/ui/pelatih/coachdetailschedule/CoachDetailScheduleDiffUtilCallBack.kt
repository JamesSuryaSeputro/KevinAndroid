package com.beone.kevin.ui.pelatih.coachdetailschedule

import androidx.recyclerview.widget.DiffUtil
import com.beone.kevin.remote.model.DetailJadwalPelatihModel

class CoachDetailScheduleDiffUtilCallBack(val oldData: DetailJadwalPelatihModel, val newData: DetailJadwalPelatihModel) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.get(oldItemPosition).id_jadwal_detail == newData.get(newItemPosition).id_jadwal_detail
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