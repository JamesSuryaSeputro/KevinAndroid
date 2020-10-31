package com.beone.kevin.ui.user.scheduleuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beone.kevin.R
import com.beone.kevin.remote.model.DetailJadwalPelatihModelItem
import kotlinx.android.synthetic.main.jadwal_user_item_detail.view.*

class DetailScheduleUserAdapter(private val children: List<DetailJadwalPelatihModelItem>) :
    RecyclerView.Adapter<DetailScheduleUserAdapter.ScheduleUserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleUserViewHolder {
        return ScheduleUserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.jadwal_user_item_detail, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ScheduleUserViewHolder, position: Int) {
        holder.bind(children[position])
    }

    override fun getItemCount() = children.size

    class ScheduleUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: DetailJadwalPelatihModelItem) = with(itemView) {

            tv_hariuser.text = item.hari + ", "
            tv_tanggaluser.text = item.tanggal
            tv_jadwaljammulaiuser.text = " (" + item.jam_mulai + " - "
            tv_jadwaljamselesaiuser.text = item.jam_selesai + ")"
            if(item.status_presensi.equals(1)) {
                tv_absensiuser.text = "Hadir"
            } else {
                tv_absensiuser.text = "Tidak Hadir"
            }
        }
    }
}