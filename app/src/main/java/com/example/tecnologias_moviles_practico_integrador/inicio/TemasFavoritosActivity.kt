package com.example.tecnologias_moviles_practico_integrador.inicio

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.Util.PreferenceUtil
import com.example.tecnologias_moviles_practico_integrador.configuraciones.ConfiguracionesActivity
import com.example.tecnologias_moviles_practico_integrador.data.ItemFavorito
import com.example.tecnologias_moviles_practico_integrador.data.ItemMuseoTema
import com.example.tecnologias_moviles_practico_integrador.data.ItemMuseoTemaGallery
import com.example.tecnologias_moviles_practico_integrador.data.Usuario
import com.example.tecnologias_moviles_practico_integrador.data.repository.ItemMuseoRepository
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityTemasFavoritosBinding
import com.example.tecnologias_moviles_practico_integrador.editar_informacion.EditarInformacionActivity
import com.example.tecnologias_moviles_practico_integrador.inicio.recycle_view.Tema
import com.example.tecnologias_moviles_practico_integrador.login.LoginActivity
import com.example.tecnologias_moviles_practico_integrador.qr.visor.VisorQrActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TemasFavoritosActivity : AppCompatActivity(), RecyclerViewOnClickListener,
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var temaListFavorito: List<ItemFavorito>
    private val itemMuseoWorker: ItemMuseoRepository = ItemMuseoRepository(this)
    private var temaList: ItemMuseoTema = ItemMuseoTema(emptyList())
    private var temaListAux: MutableList<ItemMuseoTemaGallery> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTemasFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTemas()
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

    fun recyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_temas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TemasAdapter(temaList, this)
    }


    private fun initTemas() {
        GlobalScope.launch {
            temaListFavorito = itemMuseoWorker.getFavoritos(Usuario.userInstance.nombre_usuario)!!
            for (i in 0 until temaListFavorito.size) {
                temaListAux.add(
                    ItemMuseoTemaGallery(
                        temaListFavorito[i].id,
                        temaListFavorito[i].titulo,
                        temaListFavorito[i].roomName
                    )
                )
            }
            temaList.item_gallery = temaListAux
            recyclerView()
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(
            baseContext,
            "Apreto el tema: ${temaList.item_gallery[position].titulo}",
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(this, VisorQrActivity::class.java)
        intent.putExtra("nombre_tema", temaList.item_gallery[position].titulo)
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
            val preference = PreferenceUtil(this)
            preference.setLogOut()
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
