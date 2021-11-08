package com.example.tecnologias_moviles_practico_integrador.data.repository

import android.content.Context
import com.example.tecnologias_moviles_practico_integrador.dao.DataBase
import com.example.tecnologias_moviles_practico_integrador.data.Usuario
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class UsuarioRepository(context: Context) {

    private val usuarioDb = DataBase(context)


    fun insertUsuario(usuario: Usuario) {
        GlobalScope.launch {
            try {
                usuarioDb.usuarioDao()?.insert(usuario)
            } catch (e: RuntimeException) {
                throw e
            }
        }
    }

    fun selectUsuario(nombreUsuario: String) {
        GlobalScope.launch {
            try {
                usuarioDb.usuarioDao()?.selectUsuario(nombreUsuario)
            } catch (e: RuntimeException) {
                throw e
            }
        }
    }

    fun updateNombre(nuevoNombre: String, nombreUsuario: String) {
        GlobalScope.launch {
            try {
                usuarioDb.usuarioDao()?.updateNombre(nuevoNombre, nombreUsuario)
            } catch (e: RuntimeException) {
                throw e
            }
        }

    }

    fun updateNombreUsuario(nuevoNombreUsuario: String, nombreUsuario: String) {
        GlobalScope.launch {
            try {
                usuarioDb.usuarioDao()?.updateNombreUsuario(nuevoNombreUsuario, nombreUsuario)
            } catch (e: RuntimeException) {
                throw e
            }
        }
    }


    fun updateMail(nuevoMail: String, nombreUsuario: String) {
        GlobalScope.launch {
            try {
                usuarioDb.usuarioDao()?.updateMail(nuevoMail, nombreUsuario)
            } catch (e: RuntimeException) {
                throw e
            }
        }
    }


    fun updateApellido(nuevoApellido: String, nombreUsuario: String) {
        GlobalScope.launch {
            try {
                usuarioDb.usuarioDao()?.updateApellido(nuevoApellido, nombreUsuario)
            } catch (e: RuntimeException) {
                throw e
            }
        }
    }

    fun updateContrasenia(nuevoContrasenia: String, nombreUsuario: String) {
        GlobalScope.launch {
            try {
                usuarioDb.usuarioDao()?.updateContrasenia(nuevoContrasenia, nombreUsuario)
            } catch (e: RuntimeException) {
                throw e
            }
        }
    }
}