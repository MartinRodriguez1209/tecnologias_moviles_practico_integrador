package com.example.tecnologias_moviles_practico_integrador.dao

import androidx.room.*
import com.example.tecnologias_moviles_practico_integrador.data.ItemFavorito
import com.example.tecnologias_moviles_practico_integrador.data.Usuario


@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(usuario: Usuario)


    @Query("SELECT * FROM tabla_usuario")
    suspend fun getAll(): List<Usuario>

    @Query("SELECT * FROM tabla_usuario WHERE nombre_usuario = :nombredeusuario")
    suspend fun selectUsuario(nombredeusuario: String): Usuario

    @Query("SELECT * FROM tabla_usuario WHERE nombre_usuario = :nombredeusuario AND contrasenia = :contrasenia")
    suspend fun selectUsuarioLogin(nombredeusuario: String, contrasenia: String): Usuario

    @Query("UPDATE tabla_usuario SET nombre = :nuevoNobre , apellido= :nuevoApellido  WHERE nombre_usuario = :nombreUsuario")
    suspend fun updateNombre(nuevoNobre: String, nombreUsuario: String, nuevoApellido: String)

    @Query("UPDATE tabla_usuario SET nombre_usuario = :nuevoNobreUsuario WHERE nombre_usuario = :nombreUsuario")
    suspend fun updateNombreUsuario(nuevoNobreUsuario: String, nombreUsuario: String)

    @Query("UPDATE tabla_usuario SET apellido = :nuevoApellido WHERE nombre_usuario = :nombreUsuario")
    suspend fun updateApellido(nuevoApellido: String, nombreUsuario: String)

    @Query("UPDATE tabla_usuario SET mail = :nuevoMail WHERE nombre_usuario = :nombreUsuario")
    suspend fun updateMail(nuevoMail: String, nombreUsuario: String)

    @Query("UPDATE tabla_usuario SET contrasenia = :nuevoContrasenia WHERE nombre_usuario = :nombreUsuario")
    suspend fun updateContrasenia(nuevoContrasenia: String, nombreUsuario: String)


}

@Dao
interface ItemFavoritoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(temaFavorito: ItemFavorito)

    @Query("SELECT * FROM tabla_tema_favorito WHERE nombre_usuario = :nombredeusuario")
    suspend fun selectTodosFavoritos(nombredeusuario: String): List<ItemFavorito>

    @Query("DELETE FROM tabla_tema_favorito WHERE nombre_usuario = :nombredeusuario AND id = :id")
    suspend fun deleteFavorito(nombredeusuario: String, id: String)

    @Query("SELECT * FROM tabla_tema_favorito WHERE nombre_usuario = :nombreUsuario AND id= :idItem")
    suspend fun selectFavorito(idItem: String, nombreUsuario: String): ItemFavorito


}