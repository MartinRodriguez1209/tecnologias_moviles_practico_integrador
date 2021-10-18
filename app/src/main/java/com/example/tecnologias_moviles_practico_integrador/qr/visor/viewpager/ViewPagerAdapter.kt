package com.example.tecnologias_moviles_practico_integrador.qr.visor.viewpager


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.pruebas.PruebaViewPagerActivity
import java.util.Objects;


class ViewPagerAdapter(pruebaViewPager: PruebaViewPagerActivity, urls: Array<String>) :
    PagerAdapter() {


    private var context = pruebaViewPager.applicationContext
    private var images = urls
    private lateinit var imageView: ImageView
    private var mLayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val itemView = mLayoutInflater.inflate(R.layout.item_prueba, container, false)
        imageView = itemView.findViewById<View>(R.id.imageView_prueba) as ImageView
        Glide.with(context).load(images[position]).into(imageView)
        Objects.requireNonNull(container).addView(itemView)
        return itemView!!

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        destroyItem(container, position, `object`)
        Glide.with(context).clear(imageView)
        Glide.get(context).clearMemory()
    }

}