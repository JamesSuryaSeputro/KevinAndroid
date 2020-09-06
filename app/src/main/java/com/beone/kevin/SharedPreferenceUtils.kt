package com.beone.kevin

import android.content.SharedPreferences

class SharedPreferenceUtils(sharedPref: SharedPreferences) {
    var editor = sharedPref.edit()

    fun putIdUser(idUser: String?) {
        editor.clear().apply()
        editor.putString("IDUSER", idUser).commit()
    }


    fun removeIdUser(){
        editor.clear().commit()
    }

    val getIdUser = sharedPref.getString("IDUSER", "NO ID")
}