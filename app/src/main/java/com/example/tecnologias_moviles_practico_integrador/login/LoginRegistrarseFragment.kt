package com.example.tecnologias_moviles_practico_integrador.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.tecnologias_moviles_practico_integrador.R

class LoginRegistrarseFragment : Fragment() {


    private lateinit var registrarse_button: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_registrarse, container, false)

        registrarse_button = view.findViewById(R.id.button_crear_cuenta)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrarse_button.setOnClickListener() {
            val direction =
                LoginRegistrarseFragmentDirections.actionLoginRegistrarseFragmentToLoginFragment()
            Navigation.findNavController(view).navigate(direction)
        }


    }
}