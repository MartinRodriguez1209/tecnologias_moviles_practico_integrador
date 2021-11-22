package com.example.tecnologias_moviles_practico_integrador.Util

import android.content.Context
import com.example.tecnologias_moviles_practico_integrador.dao.DataBase
import com.example.tecnologias_moviles_practico_integrador.data.Usuario

public class Utilities() {

    companion object {


        suspend fun usuarioRefresh(nombreUsuario: String, context: Context) {
            val usuarioDao = DataBase.invoke(context).usuarioDao()
            val usuario = usuarioDao?.selectUsuario(nombreUsuario)
            usuario?.let { it1 -> Usuario.instanceUser(it1) }
        }
    }
}