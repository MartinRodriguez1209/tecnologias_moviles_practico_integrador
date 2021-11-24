package com.example.tecnologias_moviles_practico_integrador.data

import com.google.gson.annotations.SerializedName

data class ItemMuseoTema(
    @SerializedName("items_list")
    var item_gallery: List<ItemMuseoTemaGallery>

)

data class ItemMuseoTemaGallery(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val titulo: String,
    @SerializedName("room_name")
    val itemMuseoTemaRoom: String
)