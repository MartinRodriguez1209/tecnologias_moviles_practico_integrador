package com.example.tecnologias_moviles_practico_integrador.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.data.repository.UsuarioRepository
import com.example.tecnologias_moviles_practico_integrador.databinding.FragmentLoginCambiarContraseniaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginCambiarContraseniaFragment : Fragment() {


    private lateinit var registrarse_button: Button
    private lateinit var binding: FragmentLoginCambiarContraseniaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_cambiar_contrasenia, container, false)
        binding = FragmentLoginCambiarContraseniaBinding.bind(view)
        registrarse_button = view.findViewById(R.id.button_cambiar_contrasenia)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrarse_button.setOnClickListener() {

            val usuarioWorker = UsuarioRepository(this.requireContext())

            GlobalScope.launch {
                try {
                    usuarioWorker.updateContrasenia(
                        binding.editTextContrasenia.text.toString(),
                        binding.editTextUsuario.text.toString()
                    )
                } catch (e: Throwable) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, getString(R.string.cambio_contrasenia_fallo), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            val direction =
                LoginCambiarContraseniaFragmentDirections.actionLoginCambiarContraseniaFragmentToLoginFragment()
            Navigation.findNavController(view).navigate(direction)
        }
    }
}