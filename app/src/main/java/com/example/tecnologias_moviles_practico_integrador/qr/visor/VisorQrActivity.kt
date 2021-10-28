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
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.configuraciones.ConfiguracionesActivity

import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityVisorQrBinding
import com.example.tecnologias_moviles_practico_integrador.editar_informacion.EditarInformacionActivity

import com.example.tecnologias_moviles_practico_integrador.inicio.TemasFavoritosActivity
import com.example.tecnologias_moviles_practico_integrador.login.LoginActivity
import com.example.tecnologias_moviles_practico_integrador.pruebas.PruebaViewPagerAdapter2
import com.google.android.material.navigation.NavigationView

class VisorQrActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private val urls = arrayOf(
        "https://cdn.discordapp.com/attachments/733856868694491146/900569482831994890/Puepis_Linda.JPG","https://cdn.discordapp.com/attachments/733856868694491146/900569670342545458/unknown.png","https://ichef.bbci.co.uk/news/640/cpsprodpb/150EA/production/_107005268_gettyimages-611696954.jpg",
        "https://cdn.britannica.com/q:60/91/181391-050-1DA18304/cat-toes-paw-number-paws-tiger-tabby.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Cat_November_2010-1a.jpg/767px-Cat_November_2010-1a.jpg",
        "https://steamuserimages-a.akamaihd.net/ugc/1661229473514719956/AA7CB1965DB65B7F07CF9B08E84CE5F0B0CCE0D0/?imw=637&imh=358&ima=fit&impolicy=Letterbox&imcolor=%23000000&letterbox=true",
        "https://i.natgeofe.com/n/3861de2a-04e6-45fd-aec8-02e7809f9d4e/02-cat-training-NationalGeographic_1484324.jpg","https://media.istockphoto.com/photos/european-short-haired-cat-picture-id1072769156?k=20&m=1072769156&s=612x612&w=0&h=k6eFXtE7bpEmR2ns5p3qe_KYh098CVLMz4iKm5OuO6Y=",
        "https://static.independent.co.uk/2021/06/16/08/newFile-4.jpg?width=982&height=726&auto=webp&quality=75","https://i.redd.it/fkwh3urmz7931.jpg","https://i.pinimg.com/originals/e3/cb/60/e3cb607b361b35adb7444a10bf340723.png","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVYZGB2y_eWS6Wo7YQvTZzCtDAkJh3R-AG8Q&usqp=CAU"
    )
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVisorQrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init(binding)
        val nombre_tema: String?
        nombre_tema = intent.getStringExtra("nombre_tema")
        if (nombre_tema != null) {
            binding.textViewToolbar.setText(nombre_tema)
        }
        viewPager()
        navMenu()
    }

    fun viewPager(){
        val adapter = PruebaViewPagerAdapter2(urls, this)
        viewPager2 = findViewById(R.id.viewPager_imagenes)
        viewPager2.adapter = adapter
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