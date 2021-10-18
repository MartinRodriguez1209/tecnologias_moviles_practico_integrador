package com.example.tecnologias_moviles_practico_integrador.qr.visor

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.configuraciones.ConfiguracionesActivity

import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityVisorQrBinding
import com.example.tecnologias_moviles_practico_integrador.editar_informacion.EditarInformacionActivity

import com.example.tecnologias_moviles_practico_integrador.inicio.TemasFavoritosActivity
import com.example.tecnologias_moviles_practico_integrador.login.LoginActivity
import com.google.android.material.navigation.NavigationView

class VisorQrActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout

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
            binding.textViewToolbar.setText(nombre_tema)
        }
        navMenu()


    }

    fun navMenu() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false);
        drawer = findViewById(R.id.drawer_temas)
        val drawerToggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navigationView = findViewById<NavigationView>(R.id.navigation_view_temas)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nav_cuenta) {
            val loginIntent = Intent(this, EditarInformacionActivity::class.java)
            startActivity(loginIntent)
        } else if (id == R.id.nav_exit) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK) //limpia el stack del boton back para que la proxima vez que se use se cierra la app
            startActivity(intent)
        } else if (id == R.id.nav_favoritos) {
            val intent = Intent(this, TemasFavoritosActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_contacto_devs) {
            val email = Intent(Intent.ACTION_SENDTO)
            email.data = Uri.parse("mailto:devs.mail@gmail.com")
            email.putExtra(Intent.EXTRA_SUBJECT, "Sugerencia app")
            startActivity(email)
        } else if (id == R.id.nav_config) {
            val intent = Intent(this, ConfiguracionesActivity::class.java)
            startActivity(intent)
        }
        drawer.closeDrawer(GravityCompat.START);
        return true
    }


}