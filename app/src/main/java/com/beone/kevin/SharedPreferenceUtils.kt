package com.beone.kevin

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtils(sharedPref: SharedPreferences) {
    var editor = sharedPref.edit()

    fun putIdUser(idUser: String?) {
        editor.clear().apply()
        editor.putString("IDUSER", idUser).commit()
    }
    val getIdUser = sharedPref.getString("IDUSER","NO ID")
}