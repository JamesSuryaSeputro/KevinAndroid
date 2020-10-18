//package com.beone.kevin.ui.pelatih.addnilai
//
//import android.text.Editable
//import android.text.TextWatcher
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.beone.kevin.CustomImageUtils
//import com.beone.kevin.R
//import com.beone.kevin.remote.model.UserModel
//import com.beone.kevin.remote.model.UserModelItem
//import com.bumptech.glide.Glide
//import kotlinx.android.synthetic.main.add_item_nilai_user.view.*
//import kotlinx.android.synthetic.main.select_tki_item.view.*
//import kotlinx.android.synthetic.main.select_tki_item.view.img_profile
//import kotlinx.android.synthetic.main.select_tki_item.view.tv_gender
//import kotlinx.android.synthetic.main.select_tki_item.view.tv_nama
//
//class NilaiAdapter(val onSelectTkiListener: OnSelectTkiListener) :
//    RecyclerView.Adapter<NilaiAdapter.SelectTkiViewHolder>() {
//
//    private var data: UserModel = UserModel()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTkiViewHolder {
//        return SelectTkiViewHolder(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.add_item_nilai_user, parent, false)
//        )
//    }
//
//    override fun getItemCount() = data.size
//
//    override fun onBindViewHolder(holder: SelectTkiViewHolder, position: Int) =
//        holder.bind(data[position],onSelectTkiListener)
//
//    fun swapData(newdata: UserModel) {
//        val diffUtil = DiffUtil.calculateDiff(SelectTkiDiffUtilCallback(data, newdata))
//        this.data.clear()
//        this.data.addAll(newdata)
//        diffUtil.dispatchUpdatesTo(this)
//    }
//
//    class SelectTkiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(item: UserModelItem, onSelectTkiListener: OnSelectTkiListener) = with(itemView) {
//            Glide.with(itemView).load(CustomImageUtils.stringToBitmap(item.passfoto))
//                .into(img_profiles)
//
//            tv_nama.text = item.nama
//            when(item.jeniskelamin){
//                "1" -> tv_gender.text = "Pria"
//                "2" -> tv_gender.text = "Wanita"
//            }
//
//            if (item.nilai !=null){
//                edt_nilai.setText(item.nilai)
//            }
//
//
//            edt_nilai.addTextChangedListener(object : TextWatcher {
//                override fun afterTextChanged(p0: Editable?) {
//
//                }
//
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                    onSelectTkiListener.onAddTki(item.id, p0.toString())
//                }
//
//            })
//        }
//    }
//
//    class SelectTkiDiffUtilCallback(val oldData: UserModel, val newData: UserModel) :
//        DiffUtil.Callback() {
//        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            return oldData.get(oldItemPosition).id == newData.get(newItemPosition).id
//        }
//
//        override fun getOldListSize(): Int = oldData.size
//
//        override fun getNewListSize(): Int = newData.size
//
//        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            return oldData.get(oldItemPosition).equals(newData.get(newItemPosition))
//        }
//
//    }
//
//    interface OnSelectTkiListener {
//        fun onAddTki(idUser: String?,nilai:String?)
//    }
//
//}