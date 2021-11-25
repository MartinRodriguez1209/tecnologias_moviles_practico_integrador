package com.example.tecnologias_moviles_practico_integrador.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.util.Utilities
import com.example.tecnologias_moviles_practico_integrador.data.Usuario
import com.example.tecnologias_moviles_practico_integrador.data.repository.UsuarioRepository
import com.example.tecnologias_moviles_practico_integrador.databinding.FragmentLoginRegistrarseBinding

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginRegistrarseFragment : Fragment() {

    private lateinit var binding: FragmentLoginRegistrarseBinding
    private lateinit var registrarse_button: Button
    private lateinit var usuario: Usuario
    private lateinit var usuarioWorker: UsuarioRepository
    private var validation: Boolean = true


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
            if (binding.editTextMail.text.isEmpty() or !Utilities.mailValidation(binding.editTextMail.text.toString())) {
                validation = false
                Toast.makeText(
                    this.requireContext(),
                    "El mail debe tener un formato valido",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (validation) {
                usuario = Usuario(
                    id = null,
                    nombre_usuario = binding.editTextUsuario.text.toString(),
                    nombre = binding.editTextNombre.text.toString(),
                    apellido = binding.editTextApellido.text.toString(),
                    mail = binding.editTextMail.text.toString(),
                    contrasenia = binding.editTextContrasenia.text.toString()
                )
                usuarioWorker = UsuarioRepository(this.requireContext())
                GlobalScope.launch {
                    usuarioWorker.insertUsuario(usuario)
                }
                val direction =
                    LoginRegistrarseFragmentDirections.actionLoginRegistrarseFragmentToLoginFragment()
                Navigation.findNavController(view).navigate(direction)
            }
        }
    }
}