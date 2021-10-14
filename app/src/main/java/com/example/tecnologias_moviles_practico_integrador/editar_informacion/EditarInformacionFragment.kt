package com.example.tecnologias_moviles_practico_integrador.editar_informacion

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.graphics.Path
import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBindings
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityLoginBinding
import com.example.tecnologias_moviles_practico_integrador.databinding.FragmentEditarInformacionBinding
import com.example.tecnologias_moviles_practico_integrador.inicio.InicioMenuActivity
import com.example.tecnologias_moviles_practico_integrador.inicio.TemasActivity
import com.example.tecnologias_moviles_practico_integrador.login.LoginActivity

class EditarInformacionFragment : Fragment() {

    private lateinit var nombreButton: ImageView
    private lateinit var nombreUsuarioButton: ImageView
    private lateinit var mailButton: ImageView
    private lateinit var contraseniaButton: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editar_informacion, container, false)
        nombreButton = view.findViewById(R.id.imageView_boton_nombre_personal)
        nombreUsuarioButton = view.findViewById(R.id.imageView_boton_nombre_usuario)
        mailButton = view.findViewById(R.id.imageView_boton_mail)
        contraseniaButton = view.findViewById(R.id.button_cambio_contrasenia)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        nombreUsuarioButton.setOnClickListener() {
            val direction =
                EditarInformacionFragmentDirections.actionEditarInformacionFragmentToEditarNombreUsuarioFragment()
            Navigation.findNavController(view).navigate(direction)
        }


        mailButton.setOnClickListener() {
            val direction =
                EditarInformacionFragmentDirections.actionEditarInformacionFragmentToEditarMailFragment()
            Navigation.findNavController(view).navigate(direction)
        }


        nombreButton.setOnClickListener {
            val direction =
                EditarInformacionFragmentDirections.actionEditarInformacionFragmentToEditarNombreFragment2()
            Navigation.findNavController(view).navigate(direction)
        }


        contraseniaButton.setOnClickListener() {
            val direction =
                EditarInformacionFragmentDirections.actionEditarInformacionFragmentToEditarContraseniaFragment()
            Navigation.findNavController(view).navigate(direction)
        }


/*        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(activity, TemasActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }*/
    }



}