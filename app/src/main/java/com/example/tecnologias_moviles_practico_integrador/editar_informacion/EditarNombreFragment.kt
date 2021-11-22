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
import com.example.tecnologias_moviles_practico_integrador.Util.Utilities
import com.example.tecnologias_moviles_practico_integrador.data.Usuario
import com.example.tecnologias_moviles_practico_integrador.data.repository.UsuarioRepository
import com.example.tecnologias_moviles_practico_integrador.databinding.FragmentEditarNombreBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditarNombreFragment : Fragment() {

    private lateinit var myFirstFragmentButton: Button
    private lateinit var binding: FragmentEditarNombreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editar_nombre, container, false)
        myFirstFragmentButton = view.findViewById(R.id.button_siguiente)
        binding = FragmentEditarNombreBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val direction =
            EditarNombreFragmentDirections.actionEditarNombreFragmentToEditarInformacionFragment()
        myFirstFragmentButton.setOnClickListener {
            val context = this.requireContext()
            var usuarioWorker = UsuarioRepository(this.requireContext())
            GlobalScope.launch {
                usuarioWorker.updateNombre(
                    binding.editTextUsuario.text.toString(),
                    Usuario.userInstance.nombre_usuario, binding.editTextApelldio.text.toString()
                )
                Utilities.usuarioRefresh(Usuario.userInstance.nombre_usuario, context)
            }
            Navigation.findNavController(view).navigate(direction)
        }
    }


}