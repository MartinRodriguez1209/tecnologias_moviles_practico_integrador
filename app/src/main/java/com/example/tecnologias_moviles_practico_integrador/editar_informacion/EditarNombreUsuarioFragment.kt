package com.example.tecnologias_moviles_practico_integrador.editar_informacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.tecnologias_moviles_practico_integrador.R
import com.example.tecnologias_moviles_practico_integrador.util.Utilities
import com.example.tecnologias_moviles_practico_integrador.data.Usuario
import com.example.tecnologias_moviles_practico_integrador.data.repository.UsuarioRepository
import com.example.tecnologias_moviles_practico_integrador.databinding.FragmentEditarNombreUsuarioBinding
import com.example.tecnologias_moviles_practico_integrador.util.PreferenceUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditarNombreUsuarioFragment : Fragment() {

    private lateinit var myFirstFragmentButton: Button
    private lateinit var binding: FragmentEditarNombreUsuarioBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editar_nombre_usuario, container, false)
        myFirstFragmentButton = view.findViewById(R.id.button_nombre_usuario_siguiente)
        binding = FragmentEditarNombreUsuarioBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val direction =
            EditarNombreUsuarioFragmentDirections.actionEditarNombreUsuarioFragmentToEditarInformacionFragment()
        myFirstFragmentButton.setOnClickListener {
            val context = this.requireContext()
            var usuarioWorker = UsuarioRepository(this.requireContext())
            GlobalScope.launch {
                usuarioWorker.updateNombreUsuario(
                    binding.editTextUsuario.text.toString(),
                    Usuario.userInstance.nombre_usuario
                )
                val preference = PreferenceUtil(context)
                preference.setUserLogin(binding.editTextUsuario.text.toString())
                Utilities.usuarioRefresh(binding.editTextUsuario.text.toString(), context)
            }
            Navigation.findNavController(view).navigate(direction)
        }
    }


}