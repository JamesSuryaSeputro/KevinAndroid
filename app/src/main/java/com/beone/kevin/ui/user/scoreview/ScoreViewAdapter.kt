package com.beone.kevin.ui.user.scoreview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beone.kevin.R
import com.beone.kevin.remote.model.JadwalModel
import kotlinx.android.synthetic.main.jadwal_user_item.view.*

class ScoreViewAdapter : RecyclerView.Adapter<ScoreViewAdapter.ScoreViewViewHolder>() {

    private var data = ArrayList<JadwalModel>()
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewViewHolder {
        return ScoreViewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.jadwal_user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ScoreViewViewHolder, position: Int) {
        holder.bind(data[position])
        val childLayoutManager = LinearLayoutManager(holder.recyclerView.context, RecyclerView.VERTICAL, false)
        childLayoutManager.initialPrefetchItemCount = 4

        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter = DetailScoreViewAdapter(data[position].jadwaldetail)
            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount() = data.size

    fun showScoreUser(list: ArrayList<JadwalModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class ScoreViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerView: RecyclerView = itemView.rcv_detailjadwaluser

        fun bind(item: JadwalModel) = with(itemView){
            tv_subjectuser.text = item.jadwalheader.id_jadwal + " - " + item.jadwalheader.nama_subject
            tv_pelatihuser.text = "Pelatih: "
            tv_namapelatihuser.text = item.jadwalheader.nama_pelatih
            tv_jadwaltanggalmulaiuser.text = item.jadwalheader.tgl_mulai + " s/d "
            tv_jadwaltanggalselesaiuser.text = item.jadwalheader.tgl_selesai
            tv_persentasekehadiran.text = "Nilai rata-rata\n${item.jadwalheader.avg_score?:0}"
        }
    }
}