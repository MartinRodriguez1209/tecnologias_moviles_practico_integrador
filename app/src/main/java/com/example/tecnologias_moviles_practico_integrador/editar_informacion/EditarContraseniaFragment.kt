package com.example.tecnologias_moviles_practico_integrador.editar_informacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.tecnologias_moviles_practico_integrador.R

class EditarContraseniaFragment : Fragment() {
    private lateinit var myFirstFragmentButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editar_contrasenia, container, false)
        myFirstFragmentButton = view.findViewById(R.id.button_editar_contrasenia)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myFirstFragmentButton.setOnClickListener {
            val direction =
                EditarContraseniaFragmentDirections.actionEditarContraseniaFragmentToEditarInformacionFragment()
            Navigation.findNavController(view).navigate(direction)
        }
    }


}