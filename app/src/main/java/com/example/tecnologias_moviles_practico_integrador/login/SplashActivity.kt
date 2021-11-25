package com.example.tecnologias_moviles_practico_integrador.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.Util.PreferenceUtil
import com.example.tecnologias_moviles_practico_integrador.data.Usuario
import com.example.tecnologias_moviles_practico_integrador.data.repository.UsuarioRepository
import com.example.tecnologias_moviles_practico_integrador.inicio.TemasActivity
import com.example.tecnologias_moviles_practico_integrador.pruebas.PruebaViewPagerActivity
import com.example.tecnologias_moviles_practico_integrador.pruebas.PruebasDataBase
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

