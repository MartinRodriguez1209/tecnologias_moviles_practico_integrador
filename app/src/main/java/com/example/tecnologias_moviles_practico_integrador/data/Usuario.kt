package com.example.tecnologias_moviles_practico_integrador.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_usuario", indices = [Index(value = ["nombre_usuario"], unique = true)])
data class Usuario(

    @NonNull
    @ColumnInfo
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "nombre_usuario") var nombre_usuario: String,
    @ColumnInfo(name = "nombre") var nombre: String,
    @ColumnInfo(name = "apellido") var apellido: String,
    @ColumnInfo(name = "mail") var mail: String,
    @ColumnInfo(name = "contrasenia") var contrasenia: String
){


    companion object UserInstance{
        @Volatile  lateinit var userInstance : Usuario
        fun instanceUser(user: Usuario){
            userInstance = user
        }
    }
}