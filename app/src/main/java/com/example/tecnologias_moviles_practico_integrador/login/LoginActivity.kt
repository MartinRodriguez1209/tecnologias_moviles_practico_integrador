package com.example.tecnologias_moviles_practico_integrador.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityEditarInformacionBinding
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityLoginBinding
import com.example.tecnologias_moviles_practico_integrador.inicio.TemasActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}
