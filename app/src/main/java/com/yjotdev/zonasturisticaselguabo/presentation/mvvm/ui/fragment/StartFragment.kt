package com.yjotdev.zonasturisticaselguabo.presentation.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import com.yjotdev.zonasturisticaselguabo.databinding.FragmentStartBinding
import com.yjotdev.zonasturisticaselguabo.R

@AndroidEntryPoint
class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Acción de botón iniciar
        binding.btnStart.setOnClickListener {
            // 1. Obtenemos el intent de la actividad
            val intent = requireActivity().intent
            // 2. Revisamos si estamos en modo test
            val isTest = intent.getBooleanExtra("IS_TESTING", false)
            val id = if (isTest) R.id.action_start_to_map_fake else R.id.action_start_to_map
            findNavController().navigate(id)
        }
    }
}