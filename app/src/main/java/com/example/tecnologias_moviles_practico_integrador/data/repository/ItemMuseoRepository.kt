package com.example.tecnologias_moviles_practico_integrador.data.repository

import android.content.Context
import androidx.room.Database
import com.example.tecnologias_moviles_practico_integrador.callbacks.ActionListenerCallback
import com.example.tecnologias_moviles_practico_integrador.callbacks.ActionListenerCallbackList
import com.example.tecnologias_moviles_practico_integrador.dao.DataBase
import com.example.tecnologias_moviles_practico_integrador.data.ItemFavorito
import com.example.tecnologias_moviles_practico_integrador.data.ItemMuseo
import com.example.tecnologias_moviles_practico_integrador.data.ItemMuseoTema
import com.example.tecnologias_moviles_practico_integrador.data.networking.NetworkingImplementation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemMuseoRepository(context: Context) {

    private var listener: ActionListenerCallback? = null
    private var listenerList: ActionListenerCallbackList? = null
    private lateinit var response: Call<ItemMuseo>
    private lateinit var qrid: String
    private lateinit var responseList: Call<ItemMuseoTema>
    private val favoritoDao = DataBase.invoke(context).ItemFavoritoDao()

    fun setQrdId(qrid: String) {
        this.qrid = qrid
    }

    fun getItemMuseum(callback: ActionListenerCallback) {
        listener = callback
        val museumService = NetworkingImplementation().service
        response = museumService.getMuseumItem()
        response.enqueue(object : Callback<ItemMuseo> {
            override fun onResponse(call: Call<ItemMuseo>, response: Response<ItemMuseo>) {
                response.body()?.let {
                    callback.onActionSuccess(it)
                }
            }

            override fun onFailure(call: Call<ItemMuseo>, t: Throwable) {
                //Falla
                callback.onActionFailure(t)
            }
        })
    }


    fun getItemMuseumTemaList(callback: ActionListenerCallbackList) {
        listenerList = callback
        val museumService = NetworkingImplementation().service
        responseList = museumService.getMuseumTemasList()
        responseList.enqueue(object : Callback<ItemMuseoTema> {
            override fun onResponse(call: Call<ItemMuseoTema>, response: Response<ItemMuseoTema>) {
                response.body()?.let {
                    callback.onActionSucces(it)
                }
            }

            override fun onFailure(call: Call<ItemMuseoTema>, t: Throwable) {
                callback.onActionFailure(t)
            }
        })
    }


    suspend fun insertItemFavorito(itemFavorito: ItemFavorito) {
        favoritoDao?.insert(itemFavorito)
    }

    suspend fun getFavoritos(nombreUsuario: String): List<ItemFavorito>? =
        favoritoDao?.selectTodosFavoritos(nombreUsuario)

    suspend fun deleteFavorito(nombreUsuario: String, id: String) {
        favoritoDao?.deleteFavorito(nombreUsuario, id)
    }

}