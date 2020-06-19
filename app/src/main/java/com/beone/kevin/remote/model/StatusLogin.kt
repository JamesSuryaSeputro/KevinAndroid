package com.beone.kevin.remote.model

import com.beone.kevin.ui.login.TypeLoginEnum

data class StatusLogin (
    var TypeLogin:TypeLoginEnum,
    var isFailedFetch:Boolean,
    val iduser:String,
    val username:String
)