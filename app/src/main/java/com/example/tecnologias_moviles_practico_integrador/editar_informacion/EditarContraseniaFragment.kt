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
import com.example.tecnologias_moviles_practico_integrador.databinding.FragmentEditarContraseniaBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditarContraseniaFragment : Fragment() {
    private lateinit var myFirstFragmentButton: Button
    private lateinit var binding: FragmentEditarContraseniaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editar_contrasenia, container, false)
        myFirstFragmentButton = view.findViewById(R.id.button_editar_contrasenia)
        binding = FragmentEditarContraseniaBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myFirstFragmentButton.setOnClickListener {
            if (binding.editTextContrasenia.text.toString() == binding.editTextTextContrasenia2.text.toString()) {
                val direction =
                    EditarContraseniaFragmentDirections.actionEditarContraseniaFragmentToEditarInformacionFragment()
                val context = this.requireContext()
                var usuarioWorker = UsuarioRepository(this.requireContext())
                GlobalScope.launch {
                    usuarioWorker.updateContrasenia(
                        binding.editTextContrasenia.text.toString(),
                        Usuario.userInstance.nombre_usuario
                    )
                    Utilities.usuarioRefresh(Usuario.userInstance.nombre_usuario, context)
                }
                Navigation.findNavController(view).navigate(direction)

            }
            else{
                Toast.makeText(this.requireContext(),getString(R.string.contrasenia_cambio_error),Toast.LENGTH_SHORT).show()
            }
        }
    }


}