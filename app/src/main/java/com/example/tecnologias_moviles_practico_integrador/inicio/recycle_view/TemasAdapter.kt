package com.example.tecnologias_moviles_practico_integrador.inicio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.inicio.recycle_view.Tema

class TemasAdapter(
    private val data: List<Tema>,
    private val listener: RecyclerViewOnClickListener
) :
    RecyclerView.Adapter<TemaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemaViewHolder {
        val row =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_tema, parent, false)
        return TemaViewHolder(row, listener)
    }


    override fun onBindViewHolder(holder: TemaViewHolder, position: Int) {
        val tema = data[position]
        holder.textView_tema.text = tema.nombre_tema
    }

    override fun getItemCount(): Int = data.size
}

class TemaViewHolder(itemView: View, listener: RecyclerViewOnClickListener) :
    RecyclerView.ViewHolder(itemView) {

    val textView_tema = itemView.findViewById<TextView>(R.id.textView_tema_nombre)

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}

interface RecyclerViewOnClickListener {

    fun onItemClick(position: Int)
}