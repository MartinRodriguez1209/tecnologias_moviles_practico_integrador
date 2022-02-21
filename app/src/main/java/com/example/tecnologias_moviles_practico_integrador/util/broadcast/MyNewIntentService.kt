package com.example.tecnologias_moviles_practico_integrador.util.broadcast

import androidx.core.app.NotificationManagerCompat

import android.app.PendingIntent

import android.content.Intent

import android.R

import android.app.IntentService
import android.app.Notification
import androidx.core.app.NotificationCompat
import com.example.tecnologias_moviles_practico_integrador.R.*
import com.example.tecnologias_moviles_practico_integrador.inicio.TemasActivity
import com.example.tecnologias_moviles_practico_integrador.util.Utilities



class MyNewIntentService : IntentService("MyNewIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        var builder = NotificationCompat.Builder(this, Utilities.channelID())
            .setSmallIcon(drawable.ic_notification_svgrepo_com)
            .setContentTitle(getString(string.notification_title))
            .setContentText(getString(string.notification_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(1, builder.build())
        }
        val notifyIntent = Intent(this, TemasActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent)
        val notificationCompat: Notification = builder.build()
        val managerCompat = NotificationManagerCompat.from(this)
        managerCompat.notify(NOTIFICATION_ID, notificationCompat)
    }

    companion object {
        private const val NOTIFICATION_ID = 3
    }
}