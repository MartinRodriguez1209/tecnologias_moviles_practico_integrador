package com.example.tecnologias_moviles_practico_integrador.configuraciones

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityConfiguracionesBinding
import com.example.tecnologias_moviles_practico_integrador.util.PreferenceUtil
import android.widget.CompoundButton
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.tecnologias_moviles_practico_integrador.util.Utilities.Companion.channelID
import com.example.tecnologias_moviles_practico_integrador.util.broadcast.AlarmReceiver
import java.util.*


class ConfiguracionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityConfiguracionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val preference = PreferenceUtil(this)

        binding.switchNotificaciones.isChecked = preference.getConfigNotificaciones()
        binding.switchNotificaciones.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                preference.setConfigNotificaciones(true)
                // TODO DatePicker
                val alarmManager = applicationContext.getSystemService(ALARM_SERVICE) as AlarmManager
                val alarmPendingIntent by lazy {
                    val intent = Intent(applicationContext, AlarmReceiver::class.java)
                    PendingIntent.getBroadcast(applicationContext, 0, intent, 0)
                }
                val HOUR_TO_SHOW_PUSH = 18

                fun schedulePushNotifications() {
                    val calendar = GregorianCalendar.getInstance().apply {
                        if (get(Calendar.HOUR_OF_DAY) >= HOUR_TO_SHOW_PUSH) {
                            add(Calendar.DAY_OF_MONTH, 1)
                        }

                        set(Calendar.HOUR_OF_DAY, HOUR_TO_SHOW_PUSH)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                        set(Calendar.MILLISECOND, 0)
                    }

                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        AlarmManager.INTERVAL_DAY,
                        alarmPendingIntent
                    )
                }
            schedulePushNotifications()
            } else {
                preference.setConfigNotificaciones(false)
            }
        }
    }
}