package com.example.tecnologias_moviles_practico_integrador.Util

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {


    var sharedPreference: SharedPreferences = context.getSharedPreferences("PREF_USER", 0)


    fun checkLogin(): Boolean? {
        return sharedPreference.getBoolean("estado", false)
    }


    fun setLogin() {
        sharedPreference.edit().putBoolean("estado", true).apply()
    }

    fun setLogOut() {
        sharedPreference.edit().putBoolean("estado", false).apply()
    }

    fun getUserLogin(): String? {
        return sharedPreference.getString("usuarioLogin", "")
    }

    fun setUserLogin(nombreUsuario: String) {
        sharedPreference.edit().putString("usuarioLogin", nombreUsuario).apply()
    }

}