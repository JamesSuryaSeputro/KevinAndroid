package com.beone.kevin.ui.pelatih.coachdetailschedule

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beone.kevin.R
import com.beone.kevin.remote.model.DetailJadwalPelatihModel
import com.beone.kevin.remote.model.DetailJadwalPelatihModelItem
import kotlinx.android.synthetic.main.jadwal_item_detail.view.*

class CoachDetailScheduleAdapter(
    val onDetailClick: OnDetailClick,
    val detailScore: Boolean,
    val detailAttendance: Boolean
) : RecyclerView.Adapter<CoachDetailScheduleAdapter.CoachDetailScheduleViewHolder>() {

    private var data: DetailJadwalPelatihModel = DetailJadwalPelatihModel()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoachDetailScheduleViewHolder {
        return CoachDetailScheduleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.jadwal_item_detail, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CoachDetailScheduleViewHolder, position: Int) {
        holder.bind(data[position], onDetailClick, detailScore, detailAttendance)
    }

    override fun getItemCount() = data.size

    fun swapData(newData: DetailJadwalPelatihModel) {
        val diffUtils = DiffUtil.calculateDiff(
            CoachDetailScheduleDiffUtilCallBack(
                data,
                newData
            )
        )
        this.data.clear()
        this.data.addAll(newData)
        diffUtils.dispatchUpdatesTo(this)
    }

    class CoachDetailScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            item: DetailJadwalPelatihModelItem,
            onDetailClick: OnDetailClick,
            detailScore: Boolean,
            detailAttendance: Boolean
        ) = with(itemView) {
            tv_hari.text = item.hari + ", "
            tv_tanggal.text = item.tanggal
            tv_jadwaljammulai.text = " (" + item.jam_mulai + "-"
            tv_jadwaljamselesai.text = item.jam_selesai + ")"
            if (detailScore) {
                btn_deletedetail.visibility = View.GONE
                setOnClickListener {
                    onDetailClick.onDetailScore(
                        item.id_jadwal_detail, item.id_jadwal, item.hari, item.tanggal, item.jam_mulai, item.jam_selesai
                    )
                }
            }

            if (detailAttendance) {
                btn_deletedetail.visibility = View.GONE
                setOnClickListener {
                    onDetailClick.onDetailAtendance(
                        item.id_jadwal_detail, item.id_jadwal, item.hari, item.tanggal, item.jam_mulai, item.jam_selesai
                    )
                }
            }

            btn_deletedetail.setOnClickListener {
                onDetailClick.onDelete(
                    item.id_jadwal_detail
                )
            }
        }
    }
}