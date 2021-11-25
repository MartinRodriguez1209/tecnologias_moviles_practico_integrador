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

    private val usuarioDao = DataBase.invoke(context).usuarioDao()
    private var listener: ActionListenerCallback? = null
    private lateinit var response: Call<Usuario>


    suspend fun loginUsuarioCoroutine(nombreUsuario: String, contrasenia: String): Usuario? =
        usuarioDao?.selectUsuarioLogin(nombreUsuario, contrasenia)


    suspend fun insertUsuario(usuario: Usuario) {
        usuarioDao?.insert(usuario)
    }

    suspend fun getUsuario(nombreUsuario: String): Usuario? {
       return usuarioDao?.selectUsuario(nombreUsuario)
    }

    suspend fun updateNombreUsuario(nuevoNombreUsuario: String, nombreUsuarioActual: String) {
        usuarioDao?.updateNombreUsuario(nuevoNombreUsuario, nombreUsuarioActual)
    }

    suspend fun updateNombre(nuevoNombre: String, nombreActual: String, nuevoApellido: String) {
        usuarioDao?.updateNombre(nuevoNombre, nombreActual, nuevoApellido)
    }

    suspend fun updateMail(nuevoMail: String, nombreUsuarioActual: String) {
        usuarioDao?.updateMail(nuevoMail, nombreUsuarioActual)
    }

    suspend fun updateContrasenia(nuevaContrasenia: String, nombreUsuarioActual: String) {
        usuarioDao?.updateContrasenia(nuevaContrasenia, nombreUsuarioActual)
    }

}