package com.example.tecnologias_moviles_practico_integrador.data.networking

import com.example.tecnologias_moviles_practico_integrador.data.ItemMuseo
import retrofit2.Call
import retrofit2.http.GET

interface MuseumService {
    @GET("v1/407de6c2-afa3-496d-b712-3b0f4ade0e14")
    fun getMuseumItem() : Call<ItemMuseo>



    @GET("https://mocki.io/v1/407de6c2-afa3-496d-b712-3b0f4ade0e14")
    fun getMuseumItemList() : Call<String>
}