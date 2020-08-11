package com.beone.kevin.ui.hrd.checkuserpayment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beone.kevin.R
import com.beone.kevin.remote.model.CheckUserDataModel
import com.beone.kevin.remote.model.CheckUserDataModelItem
import com.beone.kevin.ui.hrd.checkuserdocument.OnClickUser
import com.beone.kevin.ui.hrd.checkuserdocument.UserDataDiffUtilCallback
import kotlinx.android.synthetic.main.list_user.view.*

class UserPaymentAdapter (val onClickUser: OnClickUser) :
    RecyclerView.Adapter<UserPaymentAdapter.UserPaymentViewHolder>() {

        private var data: CheckUserDataModel = CheckUserDataModel()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPaymentViewHolder {
            return UserPaymentViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_user, parent, false)
            )
        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: UserPaymentViewHolder, position: Int) =
            holder.bind(data[position], onClickUser)

        fun swapData(newdata: CheckUserDataModel) {
            val diffUtil = DiffUtil.calculateDiff(
                UserPaymentDiffUtilCallback(
                    data,
                    newdata
                )
            )
            this.data.clear()
            this.data.addAll(newdata)
            diffUtil.dispatchUpdatesTo(this)
        }

        class UserPaymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: CheckUserDataModelItem, onClickUser: OnClickUser) = with(itemView) {
                tv_user.setText(item.nama)
                tv_datecreated.setText(item.datecreated)
                setOnClickListener {
                    onClickUser.onClick(item.iduser, item.nama)
                }
            }
        }
}