package com.example.tecnologias_moviles_practico_integrador.pruebas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room

import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.dao.DataBase
import com.example.tecnologias_moviles_practico_integrador.dao.UsuarioDao
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityPruebasDataBaseBinding
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityTemasBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.RuntimeException
import java.sql.SQLException
import com.example.tecnologias_moviles_practico_integrador.data.Usuario as Usuario1

class PruebasDataBase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPruebasDataBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)



        GlobalScope.launch {
            val daoDummy = DataBase(applicationContext)
            val us = Usuario1(
                id = null,
                nombre = "martin123",
                nombre_usuario = "martin123",
                apellido = "rodriguez123",
                mail = "martin@mail123",
                contrasenia = "123456"
            )



            try {
                daoDummy.usuarioDao()?.insert(us)
                val us2 = daoDummy.usuarioDao()?.selectUsuario("martin123")
                if (us2 != null) {
                    binding.textView.setText(us2.nombre_usuario)
                }
            }catch (e: RuntimeException){
                binding.textView.setText("usuario repetido")
            }
        }


    }
}