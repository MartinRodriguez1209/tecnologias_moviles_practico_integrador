package com.example.tecnologias_moviles_practico_integrador.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityLoginBinding
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityLoginCambiarContraseniaBinding

class LoginCambiarContraseniaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginCambiarContraseniaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init(binding)

    }

    fun init(binding: ActivityLoginCambiarContraseniaBinding) {
        binding.buttonCambiarContrasenia.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}


//TODO algun tipo de seguridad para hacer esto, mail?