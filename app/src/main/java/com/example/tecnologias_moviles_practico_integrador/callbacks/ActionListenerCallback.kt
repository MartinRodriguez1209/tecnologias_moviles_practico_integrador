package com.example.tecnologias_moviles_practico_integrador.callbacks

import com.example.tecnologias_moviles_practico_integrador.data.ItemMuseo
import com.example.tecnologias_moviles_practico_integrador.data.ItemMuseoTema

interface ActionListenerCallback {

    fun onActionSuccess(museoSucces: ItemMuseo)

    fun onActionFailure(throwableError: Throwable)
}
interface  ActionListenerCallbackList{
    fun onActionSucces(museoSucces: ItemMuseoTema)

    fun onActionFailure(throwableError: Throwable)
}
