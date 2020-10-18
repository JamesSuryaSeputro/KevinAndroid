package com.beone.kevin.ui.pelatih.schedulepelatih

interface ItemOnClick {
    fun onDelete(id:String?)
    fun onDetail(id:String?, name:String?, startdate:String?, enddate:String?)
}