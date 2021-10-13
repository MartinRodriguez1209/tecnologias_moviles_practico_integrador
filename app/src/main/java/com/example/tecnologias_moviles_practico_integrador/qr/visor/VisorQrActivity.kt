package com.example.tecnologias_moviles_practico_integrador.qr.visor

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityInicioMenuBinding
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityVisorQrBinding
import com.example.tecnologias_moviles_practico_integrador.inicio.TemasActivity
import com.example.tecnologias_moviles_practico_integrador.inicio.TemasFavoritosActivity

class VisorQrActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVisorQrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imagen = findViewById<ImageView>(R.id.imageView_imagen_qr)
        Glide.with(this)
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTLKX5e6kZvFwH36YIyy5jOBIp41XDP6h0zrw&usqp=CAU")
            .centerCrop().into(imagen)
        init(binding)
        val nombre_tema: String?
        nombre_tema = intent.getStringExtra("nombre_tema")
        if (nombre_tema != null) {
            binding.textViewTituloQr.setText(nombre_tema)
        }


    }


    fun init(binding: ActivityVisorQrBinding) {


        binding.imageViewYoutubeButton.setOnClickListener() {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + "55psWVYSbrI"))
            startActivity(intent)
            Toast.makeText(this, "ESTE BOTON LLEVA A YOUTUBE", Toast.LENGTH_SHORT).show()
        }

        binding.imageViewLinkButton.setOnClickListener() {
            Toast.makeText(this, "ESTE BOTON ES DE LINK EXTERNO", Toast.LENGTH_SHORT).show()
        }

        binding.imageViewFavoritoButton.setOnClickListener() {
            Toast.makeText(this, R.string.boton_favorito_toast, Toast.LENGTH_SHORT).show()
        }

    }


}