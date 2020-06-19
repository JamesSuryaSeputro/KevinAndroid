package com.beone.kevin.remote.model

import com.beone.kevin.ui.login.TypeLoginEnum

data class StatusLogin (
    var TypeLogin:Int,
    var isFailedFetch:Boolean = false,
    val iduser:String,
    val username:String
)