package com.beone.kevin.remote.model

data class StatusLogin (
    var TypeLogin:Int,
    var isFailedFetch:Boolean = false,
    val iduser:String,
    val username:String,
    val nama:String,
    val year:String
)