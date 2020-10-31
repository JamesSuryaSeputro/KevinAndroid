package com.beone.kevin.ui.hrd.pengirimantki

import androidx.recyclerview.widget.DiffUtil
import com.beone.kevin.remote.model.CheckUserDataModelItem
import com.beone.kevin.remote.model.ProfileUserModel

class PengirimanTkiDIffUtilCallback (val oldData: List<ProfileUserModel>, val newData: List<ProfileUserModel>) :
    DiffUtil.Callback(){
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.get(oldItemPosition).id == newData.get(newItemPosition).id
    }

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.get(oldItemPosition).equals(newData.get(newItemPosition))
    }

}