package com.example.tecnologias_moviles_practico_integrador.pruebas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.qr.visor.viewpager.ViewPagerAdapter


class PruebaViewPagerAdapter2 (private val urls: Array<String>, private val context: Context
) : RecyclerView.Adapter<PruebaViewPagerAdapter2.ViewPagerHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_prueba, parent, false)
        return ViewPagerHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind(urls[position],context)
    }

    override fun getItemCount(): Int {
        return urls.size
    }


    class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivSliderImage = itemView.findViewById<ImageView>(R.id.imageView_prueba)

        fun bind(urls: String, context: Context) {
            Glide.with(context).load(urls).into(ivSliderImage)
        }
    }
}