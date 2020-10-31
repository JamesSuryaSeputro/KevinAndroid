package com.beone.kevin.ui.pelatih.presensiujian

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beone.kevin.CustomImageUtils
import com.beone.kevin.R
import com.beone.kevin.remote.model.UserModel
import com.beone.kevin.remote.model.UserModelItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.add_item_nilai_user.view.img_profiles
import kotlinx.android.synthetic.main.add_item_nilai_user.view.linearview
import kotlinx.android.synthetic.main.add_item_nilai_user.view.tv_nama
import kotlinx.android.synthetic.main.add_presensi_item.view.*

class PresensiUjianAdapter(private val presensiUjianListener: PresensiUjianListener) :
    RecyclerView.Adapter<PresensiUjianAdapter.ViewHolder>() {

    var data: UserModel = UserModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.add_presensi_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data.get(position), presensiUjianListener, position)

    fun swapData(newdata: UserModel) {
        val diffUtil = DiffUtil.calculateDiff(PresensiUjianDiffUtilCallback(data, newdata))
        this.data.clear()
        this.data.addAll(newdata)
        diffUtil.dispatchUpdatesTo(this)
    }

    fun removeData(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: UserModelItem, presensiUjianListener: PresensiUjianListener, position: Int) =
            with(itemView) {
                tv_nama.text = item.nama
                when (item.jeniskelamin) {
                    "1" -> tv_gender.text = "Pria"
                    "2" -> tv_gender.text = "Wanita"
                }

                if (item.status_presensi.equals(1)) {
                    tv_absensi.text = "Hadir"
                } else {
                    tv_absensi.text = "Tidak Hadir"
                }

                //tv_tanggal_terdaftars.text = item.tanggalterdaftar
//                edt_nilai.visibility = View.GONE
                img_check.setOnClickListener {
                    tv_absensi.text = "Hadir"
                    presensiUjianListener.checkStatus(item.id, item.id_user)
                }
                img_clear.setOnClickListener {
                    tv_absensi.text = "Tidak Hadir"
                    presensiUjianListener.clearStatus(item.id, item.id_user)
                }

                Glide.with(this).load(CustomImageUtils.stringToBitmap(item.passfoto))
                    .into(img_profiles)
                linearview.setOnClickListener {
                    presensiUjianListener.deleteUser(position, item.id_user)
                }
            }
    }

    interface PresensiUjianListener {
        fun deleteUser(position: Int, iduser: String?)
        fun clearStatus(idpelatihan: String?, iduser: String?)
        fun checkStatus(idpelatihan: String?, iduser: String?)
    }

    class PresensiUjianDiffUtilCallback(val oldData: UserModel, val newData: UserModel) :
        DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData.get(oldItemPosition).id == newData.get(newItemPosition).id
        }

        override fun getOldListSize(): Int = oldData.size

        override fun getNewListSize(): Int = newData.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData.get(oldItemPosition).equals(newData.get(newItemPosition))
        }

    }
}