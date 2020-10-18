package com.beone.kevin.ui.pelatih.schedulepelatih

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beone.kevin.R
import com.beone.kevin.remote.model.JadwalModel
import com.beone.kevin.remote.model.JadwalPelatihModel
import com.beone.kevin.remote.model.JadwalPelatihModelItem
import kotlinx.android.synthetic.main.jadwal_item.view.*

class JadwalAdapter(val itemOnClick: ItemOnClick) : RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder>() {

    private var data = ArrayList<JadwalModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        return JadwalViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.jadwal_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: JadwalViewHolder, position: Int) =
        holder.bind(data[position],itemOnClick)

    fun swapData(newData: ArrayList<JadwalModel>) {
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
        private var isClicked: Boolean = false

        fun bind(item: JadwalModel,itemOnClick: ItemOnClick) = with(itemView) {
            tv_subject.text = item.jadwalheader.id_jadwal + " - " + item.jadwalheader.nama_subject
            tv_jadwaltanggalmulai.text = item.jadwalheader.tgl_mulai + " s/d "
            tv_jadwaltanggalselesai.text = item.jadwalheader.tgl_selesai

            setOnClickListener {
                itemOnClick.onDetail(item.jadwalheader.id_jadwal)
            }

            btn_dropdown.setOnClickListener {
                if(!isClicked) {
                    isClicked = !isClicked
                    Toast.makeText(this.context, "CLICK TRUE", Toast.LENGTH_SHORT).show()
                    rl_adddetailjadwal.visibility = View.VISIBLE
                } else {
                    isClicked = !isClicked
                    Toast.makeText(this.context, "CLICK FALSE", Toast.LENGTH_SHORT).show()
                    rl_adddetailjadwal.visibility = View.GONE
                }
            }
            rl_adddetailjadwal.setOnClickListener {
                itemOnClick.onAddDetailJadwal(item.jadwalheader.id_jadwal)
            }
            btn_delete.setOnClickListener {
                itemOnClick.onDelete(
                    item.jadwalheader.id_jadwal
                )
            }
        }
    }
}