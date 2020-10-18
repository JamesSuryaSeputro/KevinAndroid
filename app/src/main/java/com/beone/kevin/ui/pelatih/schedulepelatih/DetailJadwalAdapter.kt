package com.beone.kevin.ui.pelatih.schedulepelatih

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beone.kevin.R
import com.beone.kevin.remote.model.DetailJadwalPelatihModel
import com.beone.kevin.remote.model.DetailJadwalPelatihModelItem
import com.beone.kevin.remote.model.JadwalPelatihModel
import com.beone.kevin.remote.model.JadwalPelatihModelItem
import kotlinx.android.synthetic.main.jadwal_item_detail.view.*

class DetailJadwalAdapter : RecyclerView.Adapter<DetailJadwalAdapter.DetailJadwalViewHolder>() {

    private var data: DetailJadwalPelatihModel = DetailJadwalPelatihModel()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailJadwalViewHolder {
        return DetailJadwalViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.jadwal_item_detail, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: DetailJadwalViewHolder,
        position: Int
    ) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

//    fun swapData(newData: DetailJadwalPelatihModel) {
//        val diffUtils = DiffUtil.calculateDiff(
//            JadwalDetailDiffUtilCallBack(
//                data,
//                newData
//            )
//        )
//        this.data.clear()
//        this.data.addAll(newData)
//        diffUtils.dispatchUpdatesTo(this)
//    }

    class DetailJadwalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: DetailJadwalPelatihModelItem) = with(itemView) {
            tv_hari.text = item.hari + ", "
            tv_tanggal.text = item.tanggal
            tv_jadwaljammulai.text = item.jam_mulai
            tv_jadwaljamselesai.text = item.jam_selesai
        }
    }
}