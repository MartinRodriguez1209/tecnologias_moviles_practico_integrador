package com.example.tecnologias_moviles_practico_integrador.inicio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityInicioMenuBinding
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityLoginBinding
import com.example.tecnologias_moviles_practico_integrador.editar_informacion.EditarInformacionActivity

class InicioMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityInicioMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init(binding)
    }

    fun init(binding: ActivityInicioMenuBinding) {

        binding.imageViewQrBoton.setOnClickListener() {
            Toast.makeText(this, "Aca se abre la camara", Toast.LENGTH_LONG).show()
        }

        binding.buttonAjustes.setOnClickListener() {
            val loginIntent = Intent(this, EditarInformacionActivity::class.java)
            startActivity(loginIntent)
        }

    }
}