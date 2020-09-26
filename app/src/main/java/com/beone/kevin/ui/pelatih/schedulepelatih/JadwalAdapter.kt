package com.beone.kevin.ui.pelatih.schedulepelatih

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.beone.kevin.R
import com.beone.kevin.remote.model.JadwalPelatihModel
import com.beone.kevin.remote.model.JadwalPelatihModelItem
import kotlinx.android.synthetic.main.jadwalitem.view.*

class JadwalAdapter(val itemOnClick: ItemOnClick) : RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder>() {

    private var data: JadwalPelatihModel = JadwalPelatihModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        return JadwalViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.jadwalitem, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: JadwalViewHolder, position: Int) =
        holder.bind(data[position],itemOnClick)

    fun swapData(newData: JadwalPelatihModel) {
        val diffUtils = DiffUtil.calculateDiff(
            JadwalDiffUtilCallBack(
                data,
                newData
            )
        )
        this.data.clear()
        this.data.addAll(newData)
        diffUtils.dispatchUpdatesTo(this)

//        notifyDataSetChanged()
    }

    class JadwalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: JadwalPelatihModelItem,itemOnClick: ItemOnClick) = with(itemView) {
            tv_hari.text =  item.hari.name + ", pk. "
            tv_subject.text = item.id_jadwal + " - " + item.nama_subject
            tv_jadwaljammulai.text = item.jam_mulai + " - "
            tv_jadwaljamselesai.text = item.jam_selesai
            tv_jadwaltanggalmulai.text = item.tgl_mulai + " - "
            tv_jadwaltanggalselesai.text = item.tgl_selesai
            setOnClickListener {
                itemOnClick.onDetail(item.id_jadwal)
            }
            btn_delete.setOnClickListener {
                itemOnClick.onDelete(
                    item.id_jadwal
                )
            }
        }
    }
}