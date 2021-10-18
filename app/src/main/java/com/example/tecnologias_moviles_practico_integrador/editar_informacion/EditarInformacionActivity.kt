package com.example.tecnologias_moviles_practico_integrador.editar_informacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityEditarInformacionBinding

class EditarInformacionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditarInformacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarInformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}