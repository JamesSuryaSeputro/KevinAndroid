package com.beone.kevin.ui.pelatih.selecttkifortraining

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
import kotlinx.android.synthetic.main.schedule_pelatih_fragment.view.*
import kotlinx.android.synthetic.main.select_tki_item.view.*

class SelectTkiAdapter(val onSelectTkiListener: OnSelectTkiListener) :
    RecyclerView.Adapter<SelectTkiAdapter.SelectTkiViewHolder>() {

    private var data: UserModel = UserModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTkiViewHolder {
        return SelectTkiViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.select_tki_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: SelectTkiViewHolder, position: Int) =
        holder.bind(data[position],onSelectTkiListener)

    fun swapData(newdata: UserModel) {
        val diffUtil = DiffUtil.calculateDiff(SelectTkiDiffUtilCallback(data, newdata))
        this.data.clear()
        this.data.addAll(newdata)
        diffUtil.dispatchUpdatesTo(this)
    }

    class SelectTkiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: UserModelItem, onSelectTkiListener: OnSelectTkiListener) = with(itemView) {
            Glide.with(itemView).load(CustomImageUtils.stringToBitmap(item.passfoto))
                .into(img_profile)

            tv_nama.text = item.nama
            when(item.jeniskelamin){
                "1" -> tv_gender.text = "Pria"
                "2" -> tv_gender.text = "Wanita"
            }
            tv_tanggal_terdaftar.text = item.tanggalterdaftar
            if (item.id.isNullOrEmpty()){
                btn_delete.visibility = View.GONE
            }else{
                btn_delete.visibility = View.VISIBLE
            }
            btn_delete.setOnClickListener {
                onSelectTkiListener.onDeleteTki(item.id)
            }
            setOnClickListener {
                onSelectTkiListener.onAddTki(item.id_user)
            }
        }
    }

    class SelectTkiDiffUtilCallback(val oldData: UserModel, val newData: UserModel) :
        DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData.get(oldItemPosition).id == newData.get(newItemPosition).id
        }

        override fun getOldListSize(): Int = oldData.size

        override fun getNewListSize(): Int = newData.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData.get(oldItemPosition).equals(newData.get(newItemPosition))
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return super.getChangePayload(oldItemPosition, newItemPosition)
        }
    }

    interface OnSelectTkiListener {
        fun onAddTki(idUser: String?)
        fun onDeleteTki(idUser: String?)
    }

}