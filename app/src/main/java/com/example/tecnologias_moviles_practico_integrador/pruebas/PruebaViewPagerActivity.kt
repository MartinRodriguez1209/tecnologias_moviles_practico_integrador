package com.example.tecnologias_moviles_practico_integrador.pruebas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tecnologias_moviles_practico_integrador.R
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.tecnologias_moviles_practico_integrador.qr.visor.viewpager.ViewPagerAdapter


class PruebaViewPagerActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    lateinit var mViewPager: ViewPager
    private val urls = arrayOf(
        "https://ichef.bbci.co.uk/news/640/cpsprodpb/150EA/production/_107005268_gettyimages-611696954.jpg",
        "https://cdn.britannica.com/q:60/91/181391-050-1DA18304/cat-toes-paw-number-paws-tiger-tabby.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Cat_November_2010-1a.jpg/767px-Cat_November_2010-1a.jpg",
        "https://steamuserimages-a.akamaihd.net/ugc/1661229473514719956/AA7CB1965DB65B7F07CF9B08E84CE5F0B0CCE0D0/?imw=637&imh=358&ima=fit&impolicy=Letterbox&imcolor=%23000000&letterbox=true",
    )
    lateinit var viewpageadapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba_view_pager)
/*
        val adapter = PruebaViewPagerAdapter2(urls, this)
        viewPager2 = findViewById(R.id.view_pager_prueba)
        viewPager2.adapter = adapter
*/




    }
}