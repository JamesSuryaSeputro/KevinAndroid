package com.beone.kevin.ui.hrd.checkuserpayment

import androidx.recyclerview.widget.DiffUtil
import com.beone.kevin.remote.model.CheckUserDataModel

class UserPaymentDiffUtilCallback (val oldData: CheckUserDataModel, val newData: CheckUserDataModel) :
DiffUtil.Callback(){
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.get(oldItemPosition).iduser == newData.get(newItemPosition).iduser
    }

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.get(oldItemPosition).equals(newData.get(newItemPosition))
    }

}