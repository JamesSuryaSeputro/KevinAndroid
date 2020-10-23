package com.beone.kevin.ui.hrd.datatki

import androidx.recyclerview.widget.DiffUtil
import com.beone.kevin.remote.model.CheckUserDataModelItem

class SearchUserDiffUtilCallback (val oldData: List<CheckUserDataModelItem>, val newData: List<CheckUserDataModelItem>) :
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