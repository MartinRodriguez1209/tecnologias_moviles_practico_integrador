package com.example.tecnologias_moviles_practico_integrador.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.Util.PreferenceUtil
import com.example.tecnologias_moviles_practico_integrador.data.Usuario
import com.example.tecnologias_moviles_practico_integrador.data.repository.UsuarioRepository
import com.example.tecnologias_moviles_practico_integrador.databinding.FragmentLoginBinding
import com.example.tecnologias_moviles_practico_integrador.inicio.TemasActivity
import kotlinx.coroutines.*
import java.lang.Exception


class LoginFragment : Fragment() {

    private lateinit var login_button: Button
    private lateinit var registrarse_button: Button
    private lateinit var olvido_contrasenia_button: TextView
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        login_button = view.findViewById(R.id.button_login)
        registrarse_button = view.findViewById(R.id.button_register)
        olvido_contrasenia_button = view.findViewById(R.id.text_olvido_contrasenia)
        binding = FragmentLoginBinding.bind(view)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_button.setOnClickListener() {
            var usuarioWorker = UsuarioRepository(this.requireContext())
            var usuario: Usuario?
            val context = this.requireContext()
            var job: Job? = null
            job = GlobalScope.launch {
                try {
                    usuario = usuarioWorker.loginUsuarioCoroutine(
                        binding.editTextUsuario.text.toString(),
                        binding.editTextContrasenia.text.toString()
                    )
                    usuario?.let { it1 -> Usuario.instanceUser(it1) }
                    val intent = Intent(activity, TemasActivity::class.java)
                    intent.putExtra("nombre_usuario", usuario!!.nombre)
                    val preference = PreferenceUtil(context)
                    preference.setLogin()
                    preference.setUserLogin(binding.editTextUsuario.text.toString())
                    startActivity(intent)
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, getString(R.string.fallo_login), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
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