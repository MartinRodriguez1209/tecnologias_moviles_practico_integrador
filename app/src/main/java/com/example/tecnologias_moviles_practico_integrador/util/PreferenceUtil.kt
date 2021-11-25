package com.example.tecnologias_moviles_practico_integrador.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {

    private val ESTADOUSUARIO = "estado"
    private val ESTADOUSUARIOLOGIN = "usuarioLogin"
    private val ESTADO_SWITCH_NOTIFICACION = "notificaiones_switch"

    var sharedPreference: SharedPreferences = context.getSharedPreferences("PREF_USER", 0)


    fun checkLogin(): Boolean? {
        return sharedPreference.getBoolean(ESTADOUSUARIO, false)
    }


    fun setLogin() {
        sharedPreference.edit().putBoolean(ESTADOUSUARIO, true).apply()
    }

    fun setLogOut() {
        sharedPreference.edit().putBoolean(ESTADOUSUARIO, false).apply()
    }

    fun getUserLogin(): String? {
        return sharedPreference.getString(ESTADOUSUARIOLOGIN, "")
    }

    fun setUserLogin(nombreUsuario: String) {
        sharedPreference.edit().putString(ESTADOUSUARIOLOGIN, nombreUsuario).apply()
    }

    fun setConfigNotificaciones(valor: Boolean) {
        sharedPreference.edit().putBoolean(ESTADO_SWITCH_NOTIFICACION, valor).apply()
    }

    fun getConfigNotificaciones(): Boolean {
        return sharedPreference.getBoolean(ESTADO_SWITCH_NOTIFICACION, false)
    }

}