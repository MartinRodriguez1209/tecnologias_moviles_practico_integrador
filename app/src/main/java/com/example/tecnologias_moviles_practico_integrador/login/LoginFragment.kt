package com.example.tecnologias_moviles_practico_integrador.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.editar_informacion.EditarInformacionFragmentDirections
import com.example.tecnologias_moviles_practico_integrador.inicio.TemasActivity

class LoginFragment : Fragment() {

    private lateinit var login_button: Button
    private lateinit var registrarse_button: Button
    private lateinit var olvido_contrasenia_button: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        login_button = view.findViewById(R.id.button_login)
        registrarse_button = view.findViewById(R.id.button_register)
        olvido_contrasenia_button = view.findViewById(R.id.text_olvido_contrasenia)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_button.setOnClickListener() {
            val intent = Intent(activity, TemasActivity::class.java)
            startActivity(intent)
        }

        registrarse_button.setOnClickListener() {
            val direction = LoginFragmentDirections.actionLoginFragmentToLoginRegistrarseFragment()
            Navigation.findNavController(view).navigate(direction)
        }

        olvido_contrasenia_button.setOnClickListener() {
            val direction =
                LoginFragmentDirections.actionLoginFragmentToLoginCambiarContraseniaFragment()
            Navigation.findNavController(view).navigate(direction)
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    }
}