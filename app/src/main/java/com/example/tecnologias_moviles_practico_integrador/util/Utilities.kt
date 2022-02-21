package com.example.tecnologias_moviles_practico_integrador.util

import android.content.Context
import android.util.Patterns
import com.example.tecnologias_moviles_practico_integrador.dao.DataBase
import com.example.tecnologias_moviles_practico_integrador.data.Usuario

 class Utilities() {

    companion object {
        suspend fun usuarioRefresh(nombreUsuario: String, context: Context) {
            val usuarioDao = DataBase.invoke(context).usuarioDao()
            val usuario = usuarioDao?.selectUsuario(nombreUsuario)
            usuario?.let { it1 -> Usuario.instanceUser(it1) }
        }

        fun mailValidation(mail: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(mail).matches()

        fun channelID():String = "1001"


    }




 }