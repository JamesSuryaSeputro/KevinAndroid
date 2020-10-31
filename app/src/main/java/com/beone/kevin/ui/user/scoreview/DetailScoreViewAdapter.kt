package com.beone.kevin.ui.user.scoreview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beone.kevin.R
import com.beone.kevin.remote.model.DetailJadwalPelatihModelItem
import kotlinx.android.synthetic.main.jadwal_user_item_detail.view.*

class DetailScoreViewAdapter(private val children: List<DetailJadwalPelatihModelItem>)  : RecyclerView.Adapter<DetailScoreViewAdapter.DetailScoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailScoreViewHolder {
       return DetailScoreViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.jadwal_user_item_detail, parent, false)
       )
    }

    override fun onBindViewHolder(holder: DetailScoreViewHolder, position: Int) {
        holder.bind(children[position])
    }

    override fun getItemCount() = children.size

    class DetailScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(item: DetailJadwalPelatihModelItem) = with(itemView){
            tv_hariuser.text = item.hari + ", "
            tv_tanggaluser.text = item.tanggal
            tv_jadwaljammulaiuser.text = " (" + item.jam_mulai + " - "
            tv_jadwaljamselesaiuser.text = item.jam_selesai + ")"
            tv_absensiuser.text = item.nilai
        }
    }
}