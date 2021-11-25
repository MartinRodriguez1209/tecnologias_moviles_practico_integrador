package com.example.tecnologias_moviles_practico_integrador.inicio

import android.Manifest
import android.content.Intent
import android.content.pm.ConfigurationInfo
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.callbacks.ActionListenerCallbackList
import com.example.tecnologias_moviles_practico_integrador.configuraciones.ConfiguracionesActivity
import com.example.tecnologias_moviles_practico_integrador.data.ItemMuseoTema
import com.example.tecnologias_moviles_practico_integrador.data.repository.ItemMuseoRepository
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityTemasBinding
import com.example.tecnologias_moviles_practico_integrador.editar_informacion.EditarInformacionActivity
import com.example.tecnologias_moviles_practico_integrador.login.LoginActivity
import com.example.tecnologias_moviles_practico_integrador.qr.LectorQrActivity
import com.example.tecnologias_moviles_practico_integrador.qr.visor.VisorQrActivity
import com.google.android.material.navigation.NavigationView

class TemasActivity : AppCompatActivity(), RecyclerViewOnClickListener,
    NavigationView.OnNavigationItemSelectedListener {

    private var temaList: ItemMuseoTema = ItemMuseoTema(emptyList())
    private lateinit var drawer: DrawerLayout
    private val CAMERA_PERMISSION_CODE = 100
    private lateinit var itemMuseoWorker: ItemMuseoRepository
    private val PERMISSIONS: Array<String> = arrayOf(
        android.Manifest.permission.CAMERA
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTemasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemMuseoWorker = ItemMuseoRepository(this)
        initTemas()
        navMenu()
        binding.imageViewQrBoton.setOnClickListener() {
            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, PERMISSIONS, CAMERA_PERMISSION_CODE)
            } else {
                val loginIntent = Intent(this, LectorQrActivity::class.java)
                startActivity(loginIntent)
            }
        }
    }

    fun recyclerView(temaList: ItemMuseoTema) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_temas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TemasAdapter(temaList, this)

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

    private fun initTemas() {
        itemMuseoWorker.getItemMuseumTemaList(object : ActionListenerCallbackList {
            override fun onActionSucces(museoSucces: ItemMuseoTema) {
                temaList = museoSucces
                recyclerView(temaList)
            }

            override fun onActionFailure(throwableError: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "fallo al conseguir los temas",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, VisorQrActivity::class.java)
        intent.putExtra("nombre_tema", temaList.item_gallery[position].titulo)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
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
