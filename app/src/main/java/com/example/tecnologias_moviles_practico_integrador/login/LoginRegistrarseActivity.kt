package com.example.tecnologias_moviles_practico_integrador.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityLoginRegistrarseBinding

class LoginRegistrarseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init(binding)
    }

    fun init(binding: ActivityLoginRegistrarseBinding) {
        binding.buttonCrearCuenta.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}