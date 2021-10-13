package com.example.tecnologias_moviles_practico_integrador.editar_informacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.tecnologias_moviles_practico_integrador.R

class EditarNombreFragment : Fragment() {

    private lateinit var myFirstFragmentButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editar_nombre, container, false)
        myFirstFragmentButton = view.findViewById(R.id.button_siguiente)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myFirstFragmentButton.setOnClickListener {
            val direction = EditarNombreFragmentDirections.actionEditarNombreFragmentToEditarInformacionFragment()
            Navigation.findNavController(view).navigate(direction)
        }
    }


}