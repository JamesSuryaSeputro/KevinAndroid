package com.beone.kevin.ui.pelatih.testattendancecoach

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beone.kevin.R
import com.beone.kevin.remote.model.JadwalPelatihModel
import com.beone.kevin.remote.model.JadwalPelatihModelItem
import com.beone.kevin.ui.pelatih.schedulepelatih.JadwalDiffUtilCallBack
import kotlinx.android.synthetic.main.jadwal_item.view.*


class TestAttendanceAdapter(val onClickItem: OnClickItem) :
    RecyclerView.Adapter<TestAttendanceAdapter.TestAttendanceViewHolder>() {

    private var data: JadwalPelatihModel = JadwalPelatihModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAttendanceViewHolder {
        return TestAttendanceViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.jadwal_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TestAttendanceViewHolder, position: Int) =
        holder.bind(data[position], onClickItem)

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
    }

    class TestAttendanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: JadwalPelatihModelItem, onClickItem: OnClickItem) = with(itemView) {
            tv_subject.text = item.id_jadwal + " - " + item.nama_subject
            tv_jadwaltanggalmulai.text = item.tgl_mulai + " s/d "
            tv_jadwaltanggalselesai.text = item.tgl_selesai
            btn_delete.visibility = View.GONE

            setOnClickListener {
                onClickItem.onDetail(
                    item.id_jadwal,
                    item.nama_subject,
                    item.tgl_mulai,
                    item.tgl_selesai
                )
            }
        }
    }
}