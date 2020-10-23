package com.beone.kevin.ui.hrd.datatki

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beone.kevin.R
import com.beone.kevin.remote.model.CheckUserDataModel
import com.beone.kevin.remote.model.CheckUserDataModelItem
import com.beone.kevin.ui.hrd.checkuserdocument.UserDataDiffUtilCallback
import kotlinx.android.synthetic.main.list_user.view.*

class DataTkiAdapter(val onClickListData: OnClickListData) :
    RecyclerView.Adapter<DataTkiAdapter.DataTkiViewHolder>() {

    companion object{
        private const val TAG = "DataTkiAdapter"
    }

    private var data: CheckUserDataModel = CheckUserDataModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataTkiViewHolder {
        return DataTkiViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_user, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DataTkiViewHolder, position: Int) =
        holder.bind(data[position], onClickListData)

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

    fun searchData(newdata: List<CheckUserDataModelItem>) {
        val diffUtil = DiffUtil.calculateDiff(
            SearchUserDiffUtilCallback(
                data,
                newdata
            )
        )
        this.data.clear()
        this.data.addAll(newdata)
        diffUtil.dispatchUpdatesTo(this)
    }

    class DataTkiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CheckUserDataModelItem, onClickUser: OnClickListData) = with(itemView) {
            tv_user.text = item.nama
            tv_tgluser.text = "Active since: " + item.datecreated

            //21-2020-001
            setOnClickListener {
                onClickUser.onClick(item.iduser)
            }
        }
    }
}