package com.example.tecnologias_moviles_practico_integrador.data.repository

import android.content.Context
import com.example.tecnologias_moviles_practico_integrador.callbacks.ActionListenerCallback
import com.example.tecnologias_moviles_practico_integrador.dao.DataBase
import com.example.tecnologias_moviles_practico_integrador.data.Usuario
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import android.os.Handler
import android.os.Looper
import androidx.room.Room
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class UsuarioRepository(context: Context) {

    private val usuarioDb = DataBase(context)
    private var listener: ActionListenerCallback? = null
    private lateinit var response: Call<Usuario>



    fun loginUsuarioCoroutine(nombreUsuario: String, contrasenia: String): Usuario? = usuarioDb.usuarioDao()?.selectUsuarioLogin(nombreUsuario, contrasenia)


    fun loginUsuario(nombreUsuario: String, contrasenia: String): Usuario {
        lateinit var usuario: Usuario
        val launch = GlobalScope.launch {
            try {
                usuario =
                    usuarioDb.usuarioDao()?.selectUsuarioLogin(nombreUsuario, contrasenia)!!
            } catch (e: RuntimeException) {
                throw e
            }
        }
        return usuario
    }


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