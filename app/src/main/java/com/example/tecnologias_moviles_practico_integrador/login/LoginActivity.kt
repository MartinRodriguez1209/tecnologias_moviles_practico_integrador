package com.example.tecnologias_moviles_practico_integrador.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityLoginBinding
import com.example.tecnologias_moviles_practico_integrador.inicio.TemasActivity


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        login(binding)
    }

    fun login(binding: ActivityLoginBinding) {

        binding.buttonLogin.setOnClickListener {


            val loginIntent = Intent(this, TemasActivity::class.java)
            startActivity(loginIntent)
        }

        binding.buttonRegister.setOnClickListener {
            val registroIntent = Intent(this, LoginRegistrarseActivity::class.java)
            startActivity(registroIntent)
        }
        binding.textOlvidoContrasenia.setOnClickListener {
            val contraseniaIntent = Intent(this, LoginCambiarContraseniaActivity::class.java)
            startActivity(contraseniaIntent)
        }

    }


}

//TODO trabajar esto con fragments?