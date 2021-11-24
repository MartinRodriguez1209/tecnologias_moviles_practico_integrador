package com.example.tecnologias_moviles_practico_integrador.dao

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tecnologias_moviles_practico_integrador.data.Usuario
import androidx.room.Room
import com.example.tecnologias_moviles_practico_integrador.data.ItemFavorito


@Database(entities = arrayOf(Usuario::class, ItemFavorito::class), version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao?
    abstract fun ItemFavoritoDao(): ItemFavoritoDao?


    companion object {
        @Volatile
        private var instance: DataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            DataBase::class.java, "todo-list.db"
        )
            .build()
    }

}