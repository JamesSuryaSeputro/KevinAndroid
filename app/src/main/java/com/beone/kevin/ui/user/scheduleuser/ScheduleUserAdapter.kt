package com.beone.kevin.ui.user.scheduleuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beone.kevin.R
import com.beone.kevin.remote.model.JadwalPelatihModel
import com.beone.kevin.remote.model.JadwalPelatihModelItem
import kotlinx.android.synthetic.main.jadwal_user_item.view.*

class ScheduleUserAdapter() : RecyclerView.Adapter<ScheduleUserAdapter.ScheduleUserViewHolder>() {

    private var data: JadwalPelatihModel = JadwalPelatihModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleUserViewHolder {
        return ScheduleUserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.jadwal_user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ScheduleUserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun showScheduleUser(list: JadwalPelatihModel) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class ScheduleUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: JadwalPelatihModelItem) = with(itemView) {
            tv_subjectuser.text = item.nama_subject
            tv_pelatihuser.text = "Pelatih: "
            tv_namapelatihuser.text = item.nama_pelatih
            tv_hariuser.text = item.hari.toString() + ", "
            tv_jadwaljammulaiuser.text = "pk. " + item.jam_mulai + " - "
            tv_jadwaljamselesaiuser.text = item.jam_selesai
            tv_jadwaltanggalmulaiuser.text = item.tgl_mulai + " - "
            tv_jadwaltanggalselesaiuser.text = item.tgl_selesai
        }
    }
}