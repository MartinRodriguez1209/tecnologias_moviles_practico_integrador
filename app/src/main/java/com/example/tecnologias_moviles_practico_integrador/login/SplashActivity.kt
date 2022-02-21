package com.example.tecnologias_moviles_practico_integrador.login

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.util.PreferenceUtil
import com.example.tecnologias_moviles_practico_integrador.data.Usuario
import com.example.tecnologias_moviles_practico_integrador.data.repository.UsuarioRepository
import com.example.tecnologias_moviles_practico_integrador.inicio.TemasActivity
import com.example.tecnologias_moviles_practico_integrador.util.Utilities.Companion.channelID
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            var usuarioWorker = UsuarioRepository(this)
            val preference = PreferenceUtil(this)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Create the NotificationChannel
                val name = getString(R.string.channel_name)
                val descriptionText = getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT

                val mChannel = NotificationChannel(channelID(), name, importance)
                mChannel.description = descriptionText
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mChannel)
            }

            if (preference.checkLogin() == false) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                val intent = Intent(this, TemasActivity::class.java)
                GlobalScope.launch {
                    Usuario.userInstance = preference.getUserLogin()?.let {
                        usuarioWorker.getUsuario(
                            it
                        )
                    }!!
                }
                startActivity(intent)
                finish()
            }
        }, SPLASH_TIME_OUT)
    }
}

