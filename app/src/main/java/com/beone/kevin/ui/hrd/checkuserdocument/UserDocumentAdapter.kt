package com.beone.kevin.ui.hrd.checkuserdocument

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beone.kevin.R
import com.beone.kevin.remote.model.CheckUserDataModel
import com.beone.kevin.remote.model.CheckUserDataModelItem
import kotlinx.android.synthetic.main.list_user.view.*

class UserDocumentAdapter(val onClickUser: OnClickUser) :
    RecyclerView.Adapter<UserDocumentAdapter.UserDocumentViewHolder>() {

    private var data: CheckUserDataModel = CheckUserDataModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDocumentViewHolder {
        return UserDocumentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_user, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: UserDocumentViewHolder, position: Int) =
        holder.bind(data[position], onClickUser)

    fun swapData(newdata: CheckUserDataModel) {
        val diffUtil = DiffUtil.calculateDiff(
            UserDataDiffUtilCallback(
                data,
                newdata
            )
        )
        this.data.clear()
        this.data.addAll(newdata)
        diffUtil.dispatchUpdatesTo(this)
    }

    class UserDocumentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CheckUserDataModelItem, onClickUser: OnClickUser) = with(itemView) {
            tv_user.setText(item.nama)
            tv_datecreated.setText(item.datecreated)
            setOnClickListener {
                onClickUser.onClick(item.iduser, item.nama)
            }
        }
    }
}