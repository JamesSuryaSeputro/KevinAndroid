package com.beone.kevin

import android.content.SharedPreferences

class SharedPreferenceUtils(sharedPref: SharedPreferences) {
    var editor = sharedPref.edit()

    fun putIdUser(idUser: String?) {
        editor.putString("IDUSER", idUser).commit()
    }

    fun putNamaUser(namaUser: String?) {
        editor.putString("NAMAUSER", namaUser).commit()
    }

    fun removeUser(){
        editor.clear().commit()
    }

    val getIdUser = sharedPref.getString("IDUSER", "NO ID")
    val getNamaUser = sharedPref.getString("NAMAUSER", "NO NAME")
}