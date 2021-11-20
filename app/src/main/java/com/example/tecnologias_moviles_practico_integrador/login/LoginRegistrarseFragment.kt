package com.example.tecnologias_moviles_practico_integrador.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.dao.DataBase
import com.example.tecnologias_moviles_practico_integrador.data.Usuario
import com.example.tecnologias_moviles_practico_integrador.databinding.ActivityLoginBinding
import com.example.tecnologias_moviles_practico_integrador.databinding.FragmentLoginRegistrarseBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginRegistrarseFragment : Fragment() {

    private lateinit var binding: FragmentLoginRegistrarseBinding
    private lateinit var registrarse_button: Button
    private lateinit var usuario: Usuario


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_registrarse, container, false)
        registrarse_button = view.findViewById(R.id.button_crear_cuenta)
        binding = FragmentLoginRegistrarseBinding.bind(view)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarse_button.setOnClickListener() {
            Toast.makeText(context, binding.editTextNombre.text.toString(), Toast.LENGTH_SHORT)
                .show()
            usuario = Usuario(
                id = null,
                nombre_usuario = binding.editTextUsuario.text.toString(),
                nombre = binding.editTextNombre.text.toString(),
                apellido = binding.editTextApellido.text.toString(),
                mail = binding.editTextMail.text.toString(),
                contrasenia = binding.editTextContrasenia.text.toString()
            )
            val context = requireContext()
            GlobalScope.launch {
                val daoAcces = DataBase(context)
                daoAcces.usuarioDao()?.insert(usuario)
            }
            val direction =
                LoginRegistrarseFragmentDirections.actionLoginRegistrarseFragmentToLoginFragment()
            Navigation.findNavController(view).navigate(direction)
        }


    }
}