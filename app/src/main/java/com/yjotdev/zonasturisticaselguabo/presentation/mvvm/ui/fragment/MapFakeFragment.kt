package com.yjotdev.zonasturisticaselguabo.presentation.mvvm.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue
import com.yjotdev.zonasturisticaselguabo.databinding.FragmentMapFakeBinding
import com.yjotdev.zonasturisticaselguabo.presentation.mvvm.ui.adapter.SitesAdapter
import com.yjotdev.zonasturisticaselguabo.presentation.mvvm.viewmodel.UiViewModel
import com.yjotdev.zonasturisticaselguabo.presentation.utils.Provider
import com.yjotdev.zonasturisticaselguabo.R

@AndroidEntryPoint
class MapFakeFragment : Fragment() {

    private lateinit var binding: FragmentMapFakeBinding
    private val viewModel: UiViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapFakeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        val recyclerView = binding.recyclerViewSites
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = SitesAdapter(Provider.sites) { site ->
            // Actualizar el estado del ViewModel con los datos del sitio seleccionado
            viewModel.setTitle(getString(site.titleId))
            viewModel.setImageUrl(site.imageUrl)
            viewModel.setDescription(getString(site.descriptionId))
            //Evento que muestra un fragment de la info seleccionada
            findNavController().navigate(R.id.action_map_fake_to_info)
        }
    }

    private fun setToolbar(){
        val toolbar = binding.includeToolbar.toolbar
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Solo padding arriba para que baje y no choque con la hora
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }
    }
}