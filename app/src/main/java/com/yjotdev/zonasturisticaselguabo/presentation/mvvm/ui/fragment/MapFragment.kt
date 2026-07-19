package com.yjotdev.zonasturisticaselguabo.presentation.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import com.yjotdev.zonasturisticaselguabo.databinding.FragmentMapBinding
import com.yjotdev.zonasturisticaselguabo.presentation.mvvm.viewmodel.UiViewModel
import com.yjotdev.zonasturisticaselguabo.presentation.utils.Provider
import com.yjotdev.zonasturisticaselguabo.R

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapBinding
    private val viewModel: UiViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        // Busca el fragment del mapa y obtiene su instancia
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Posición inicial de cámara
        val posStart = LatLng(-3.2099019884381983, -79.81512207539423)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posStart, 10.5f))
        // Agregar marcas de locación
        mMap.addMarker(MarkerOptions().apply {
            position(Provider.sites[0].position)
            title(getString(Provider.sites[0].titleId))
            contentDescription(getString(Provider.sites[0].descriptionId))
        })
        mMap.addMarker(MarkerOptions().apply {
            position(Provider.sites[1].position)
            title(getString(Provider.sites[1].titleId))
            contentDescription(getString(Provider.sites[1].descriptionId))
        })
        mMap.addMarker(MarkerOptions().apply {
            position(Provider.sites[2].position)
            title(getString(Provider.sites[2].titleId))
            contentDescription(getString(Provider.sites[2].descriptionId))
        })
        mMap.addMarker(MarkerOptions().apply {
            position(Provider.sites[3].position)
            title(getString(Provider.sites[3].titleId))
            contentDescription(getString(Provider.sites[3].descriptionId))
        })
        mMap.addMarker(MarkerOptions().apply {
            position(Provider.sites[4].position)
            title(getString(Provider.sites[4].titleId))
            contentDescription(getString(Provider.sites[4].descriptionId))
        })
        //Evento que muestra la info del marcador seleccionado
        mMap.setOnMarkerClickListener {
            it.showInfoWindow()
            true
        }
        //Evento que muestra un fragment de la info seleccionada
        mMap.setOnInfoWindowClickListener {
            when(it.position){
                Provider.sites[0].position -> {
                    viewModel.setTitle(getString(Provider.sites[0].titleId))
                    viewModel.setImageUrl(Provider.sites[0].imageUrl)
                    viewModel.setDescription(getString(Provider.sites[0].descriptionId))
                }
                Provider.sites[1].position -> {
                    viewModel.setTitle(getString(Provider.sites[1].titleId))
                    viewModel.setImageUrl(Provider.sites[1].imageUrl)
                    viewModel.setDescription(getString(Provider.sites[1].descriptionId))
                }
                Provider.sites[2].position -> {
                    viewModel.setTitle(getString(Provider.sites[2].titleId))
                    viewModel.setImageUrl(Provider.sites[2].imageUrl)
                    viewModel.setDescription(getString(Provider.sites[2].descriptionId))
                }
                Provider.sites[3].position -> {
                    viewModel.setTitle(getString(Provider.sites[3].titleId))
                    viewModel.setImageUrl(Provider.sites[3].imageUrl)
                    viewModel.setDescription(getString(Provider.sites[3].descriptionId))
                }
                Provider.sites[4].position -> {
                    viewModel.setTitle(getString(Provider.sites[4].titleId))
                    viewModel.setImageUrl(Provider.sites[4].imageUrl)
                    viewModel.setDescription(getString(Provider.sites[4].descriptionId))
                }
            }
            findNavController().navigate(R.id.action_map_to_info)
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