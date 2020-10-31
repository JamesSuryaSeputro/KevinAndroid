package com.beone.kevin.ui.pelatih.addnilai

import androidx.recyclerview.widget.DiffUtil
import com.beone.kevin.remote.model.UserModel

class SelectTkiDiffUtilCallback(val oldData: UserModel, val newData: UserModel) :
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.get(oldItemPosition).id == newData.get(newItemPosition).id
    }

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.get(oldItemPosition).equals(newData.get(newItemPosition))
    }

}