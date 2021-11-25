package com.example.tecnologias_moviles_practico_integrador.data.networking

import com.example.tecnologias_moviles_practico_integrador.data.ItemMuseo
import com.example.tecnologias_moviles_practico_integrador.data.ItemMuseoTema
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface MuseumService {
    @GET("v1/407de6c2-afa3-496d-b712-3b0f4ade0e14")
    fun getMuseumItem(): Call<ItemMuseo>

    @GET("/v1/290a0013-549f-4a0a-8739-f6c7ec4ff025")
    fun getMuseumTemasList(): Call<ItemMuseoTema>

    @GET("https://mocki.io/v1/407de6c2-afa3-496d-b712-3b0f4ade0e14")
    fun getMuseumItemList(): Call<String>

    @GET
    fun getMuseumItem(@Url qr: String): Call<ItemMuseo>
}