package com.example.tecnologias_moviles_practico_integrador.util.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.util.Utilities
import com.example.tecnologias_moviles_practico_integrador.util.Utilities.Companion.channelID
import java.util.*


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        showPushNotification(context) // implement showing notification in this function
    }

    private fun showPushNotification(context: Context) {
        var builder = NotificationCompat.Builder(context, channelID())
            .setSmallIcon(R.drawable.ic_notification_svgrepo_com)
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(context.getString(R.string.notification_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(1001, builder.build())
        }
    }
}