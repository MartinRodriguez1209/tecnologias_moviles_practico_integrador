package com.example.tecnologias_moviles_practico_integrador.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_tema_favorito",indices = [Index(value = ["nombre_usuario","titulo"], unique = true)])
data class ItemFavorito(
    @NonNull
    @ColumnInfo
    @PrimaryKey(autoGenerate = true) var idDatabase: Int?,
    @ColumnInfo var id: String,
    @ColumnInfo(name = "titulo") var titulo: String,
    @ColumnInfo(name = "nombre_usuario") var nombreUsuario: String,
    @ColumnInfo(name = "room_name") var roomName: String,


    )