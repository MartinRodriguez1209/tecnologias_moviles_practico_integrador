package com.example.tecnologias_moviles_practico_integrador.util.broadcast

import android.content.Intent

import android.content.BroadcastReceiver
import android.content.Context


class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val intent1 = Intent(context, MyNewIntentService::class.java)
        context.startService(intent1)
    }
}