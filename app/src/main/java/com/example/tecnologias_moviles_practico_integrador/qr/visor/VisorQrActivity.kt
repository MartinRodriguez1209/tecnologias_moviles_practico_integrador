package com.example.tecnologias_moviles_practico_integrador.qr.visor

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.callbacks.ActionListenerCallback
import com.example.tecnologias_moviles_practico_integrador.configuraciones.ConfiguracionesActivity
import com.example.tecnologias_moviles_practico_integrador.data.ItemGallery
import com.example.tecnologias_moviles_practico_integrador.data.ItemMuseo
import com.example.tecnologias_moviles_practico_integrador.data.ItemMuseoTema
import com.example.tecnologias_moviles_practico_integrador.data.repository.ItemMuseoRepository
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityVisorQrBinding
import com.example.tecnologias_moviles_practico_integrador.editar_informacion.EditarInformacionActivity
import com.example.tecnologias_moviles_practico_integrador.inicio.TemasFavoritosActivity
import com.example.tecnologias_moviles_practico_integrador.login.LoginActivity
import com.example.tecnologias_moviles_practico_integrador.pruebas.PruebaViewPagerAdapter2
import com.google.android.material.navigation.NavigationView

class VisorQrActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var viewPager2: ViewPager2
    private val itemMuseoWorker: ItemMuseoRepository = ItemMuseoRepository(this)
    private lateinit var youtubeUrl: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVisorQrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getItemMuseo(binding)
        navMenu()
        init(binding)
    }

    fun getItemMuseo(binding: ActivityVisorQrBinding) {
        itemMuseoWorker.getItemMuseum(object : ActionListenerCallback {
            override fun onActionSuccess(museoSucces: ItemMuseo) {
                Log.i("SUCCESS", museoSucces.toString())
                binding.textViewToolbar.text = museoSucces.item_title
                binding.textViewContenido.text =
                    museoSucces.item_intro + museoSucces.item_main_content
                Toast.makeText(
                    applicationContext,
                    museoSucces.item_gallery[0].url,
                    Toast.LENGTH_SHORT
                ).show()
                viewPager(museoSucces.item_gallery)
                youtubeUrl = museoSucces.item_youtube
            }


            override fun onActionFailure(throwableError: Throwable) {
                Log.i("FAILURE", throwableError.message!!)
            }
        })
    }

    fun viewPager(urls: List<ItemGallery>) {
        val urlsArray: MutableList<String> = emptyList<String>().toMutableList()
        for (element in urls) {
            urlsArray.add(element.url)
        }
        val adapter = PruebaViewPagerAdapter2(urlsArray, this)
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
            if (youtubeUrl != null) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
                startActivity(intent)
            } else {
                Toast.makeText(this, getString(R.string.video_fallo_mensaje), Toast.LENGTH_SHORT)
                    .show()
            }
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