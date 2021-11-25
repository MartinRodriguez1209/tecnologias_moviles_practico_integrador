package com.example.tecnologias_moviles_practico_integrador.configuraciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityConfiguracionesBinding
import com.example.tecnologias_moviles_practico_integrador.util.PreferenceUtil
import android.widget.CompoundButton


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
            } else {
                preference.setConfigNotificaciones(false)
            }
        }
    }
}