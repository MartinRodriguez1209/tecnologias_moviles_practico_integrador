package com.example.tecnologias_moviles_practico_integrador.callbacks

import com.example.tecnologias_moviles_practico_integrador.data.ItemMuseo

interface ActionListenerCallback {

    fun onActionSuccess(usuarioSucces: ItemMuseo)

    fun onActionFailure(throwableError: Throwable)
}
