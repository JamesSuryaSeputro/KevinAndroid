package com.beone.kevin.ui.hrd.checkuserdocument

import androidx.recyclerview.widget.DiffUtil
import com.beone.kevin.remote.model.CheckUserDataModel

class UserDataDiffUtilCallback(val oldData: CheckUserDataModel, val newData: CheckUserDataModel) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.get(oldItemPosition).iduser == newData.get(newItemPosition).iduser
    }

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.get(oldItemPosition).equals(newData.get(newItemPosition))
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}