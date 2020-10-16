package com.beone.kevin.ui.pelatih.schedulepelatih

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import com.beone.kevin.R
import com.beone.kevin.remote.model.JadwalPelatihModel
import com.beone.kevin.remote.model.JadwalPelatihModelItem
import kotlinx.android.synthetic.main.jadwal_item.view.*
import kotlinx.android.synthetic.main.jadwal_item.view.btn_delete
import kotlinx.android.synthetic.main.jadwal_item_detail.view.*

class JadwalAdapter(val itemOnClick: ItemOnClick) : RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder>() {

    private var data: JadwalPelatihModel = JadwalPelatihModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        return JadwalViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.jadwal_item, parent, false)
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
        private var isClicked: Boolean = false

        fun bind(item: JadwalPelatihModelItem,itemOnClick: ItemOnClick) = with(itemView) {
            tv_subject.text = item.id_jadwal + " - " + item.nama_subject
            tv_jadwaltanggalmulai.text = item.tgl_mulai + " - "
            tv_jadwaltanggalselesai.text = item.tgl_selesai
            tv_hari.text = item.hari.name + ", "
            tv_tanggal.text = item.tanggal
            tv_jadwaljammulai.text = item.jam_mulai
            tv_jadwaljamselesai.text = item.jam_selesai

            setOnClickListener {
                itemOnClick.onDetail(item.id_jadwal)
            }
            btn_dropdown.setOnClickListener {
                if(!isClicked) {
                    isClicked = !isClicked
                    tv_hari.text = item.hari.name + ", "
                    tv_tanggal.text = item.tanggal
                    tv_jadwaljammulai.text = item.jam_mulai
                    tv_jadwaljamselesai.text = item.jam_selesai
                    Toast.makeText(this.context, "CLICK TRUE", Toast.LENGTH_SHORT).show()
                    rl_adddetailjadwal.visibility = View.VISIBLE
                    itemOnClick.onClickDropdown(item.id_jadwal)


                    //tv_hari.text = item.hari.toString() + ", "

//                    tv_subject.text = item.id_jadwal + " - " + item.nama_subject
//                    tv_jadwaltanggalmulai.text = item.tgl_mulai + " - "
//                    tv_jadwaltanggalselesai.text = item.tgl_selesai
//                    tv_hari.text = item.hari.toString() + ", "
//                    tv_tanggal.text = item.tanggal
//                    tv_jadwaljammulai.text = item.jam_mulai
//                    tv_jadwaljamselesai.text = item.jam_selesai
//                    rl_detailjadwal.visibility = View.VISIBLE
                    //rl_detailjadwal.visibility = View.VISIBLE
                    //rcv_detailjadwal.visibility = View.VISIBLE
                } else {
                    isClicked = !isClicked
                    Toast.makeText(this.context, "CLICK FALSE", Toast.LENGTH_SHORT).show()
                    rl_adddetailjadwal.visibility = View.GONE
                    //rl_detailjadwal.visibility = View.GONE
                    //itemOnClick.onClickDropdown(item.id_jadwal)

//                    tv_subject.text = item.id_jadwal + " - " + item.nama_subject
//                    tv_jadwaltanggalmulai.text = item.tgl_mulai + " - "
//                    tv_jadwaltanggalselesai.text = item.tgl_selesai
                    //rl_detailjadwal.visibility = View.GONE
                   // rcv_detailjadwal.visibility = View.GONE
                }
            }
            rl_adddetailjadwal.setOnClickListener {
                itemOnClick.onAddDetailJadwal(item.id_jadwal)
            }
            btn_delete.setOnClickListener {
                itemOnClick.onDelete(
                    item.id_jadwal
                )
            }
        }
    }
}