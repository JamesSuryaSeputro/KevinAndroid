package com.beone.kevin.ui.hrd.pengirimantki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beone.kevin.R
import com.beone.kevin.remote.model.CheckUserDataModel
import com.beone.kevin.remote.model.ProfileUserModel
import com.beone.kevin.remote.model.ShippingUserModel
import com.beone.kevin.ui.hrd.checkuserdocument.UserDataDiffUtilCallback
import kotlinx.android.synthetic.main.item_pengiriman_tki.view.*

class PengirimanTkiAdapter(val onClickSend: OnClickSend) :
    RecyclerView.Adapter<PengirimanTkiAdapter.PengirimanTkiViewHolder>() {

    private var data: ShippingUserModel = ShippingUserModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengirimanTkiViewHolder {
        return PengirimanTkiViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pengiriman_tki, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PengirimanTkiViewHolder, position: Int) {
        holder.bind(data[position], onClickSend)
    }

    override fun getItemCount() = data.size

    fun swapData(newdata: ShippingUserModel) {
        val diffUtil = DiffUtil.calculateDiff(
            PengirimanTkiDIffUtilCallback(
                data,
                newdata
            )
        )
        this.data.clear()
        this.data.addAll(newdata)
        diffUtil.dispatchUpdatesTo(this)
    }

    class PengirimanTkiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ProfileUserModel, onClickSend: OnClickSend) = with(itemView){
            tv_sendname.text = item.nama
            tv_sendactive.text = "Aktif sejak: " + item.year

            tv_siapdikirim.setOnClickListener {
                onClickSend.onClick(item.id)
            }
        }
    }
}