package com.example.tecnologias_moviles_practico_integrador.dao

import androidx.room.*
import com.example.tecnologias_moviles_practico_integrador.data.Usuario


@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(usuario: Usuario)


    @Query("SELECT * FROM tabla_usuario")
    fun getAll(): List<Usuario>

    @Query("SELECT * FROM tabla_usuario WHERE nombre_usuario = :nombredeusuario")
    fun selectUsuario(nombredeusuario:String):Usuario

    @Query("SELECT * FROM tabla_usuario WHERE nombre_usuario = :nombredeusuario AND contrasenia = :contrasenia" )
    fun selectUsuarioLogin(nombredeusuario:String, contrasenia:String):Usuario

    @Query("UPDATE tabla_usuario SET nombre = :nuevoNobre WHERE nombre_usuario = :nombreUsuario"  )
    fun updateNombre( nuevoNobre: String, nombreUsuario: String)

    @Query("UPDATE tabla_usuario SET nombre_usuario = :nuevoNobreUsuario WHERE nombre_usuario = :nombreUsuario"  )
    fun updateNombreUsuario( nuevoNobreUsuario: String, nombreUsuario: String)

    @Query("UPDATE tabla_usuario SET apellido = :nuevoApellido WHERE nombre_usuario = :nombreUsuario"  )
    fun updateApellido( nuevoApellido: String, nombreUsuario: String)

    @Query("UPDATE tabla_usuario SET mail = :nuevoMail WHERE nombre_usuario = :nombreUsuario"  )
    fun updateMail( nuevoMail: String, nombreUsuario: String)

    @Query("UPDATE tabla_usuario SET contrasenia = :nuevoContrasenia WHERE nombre_usuario = :nombreUsuario"  )
    fun updateContrasenia( nuevoContrasenia: String, nombreUsuario: String)





}