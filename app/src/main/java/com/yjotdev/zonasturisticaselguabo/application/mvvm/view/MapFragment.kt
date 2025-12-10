package com.yjotdev.zonasturisticaselguabo.application.mvvm.view

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
import com.yjotdev.zonasturisticaselguabo.R
import com.yjotdev.zonasturisticaselguabo.databinding.FragmentMapBinding
import com.yjotdev.zonasturisticaselguabo.application.mvvm.viewmodel.UiViewModel

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
        // Posicion inicial de camara
        val posStart = LatLng(-3.2099019884381983, -79.81512207539423)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posStart, 10.5f))
        // Agregar marcas de locacion
        val pos1 = LatLng(-3.2041386029873475, -79.73564629295399)
        mMap.addMarker(MarkerOptions().apply {
            position(pos1)
            title(getString(R.string.title_fragment_info1))
            contentDescription(getString(R.string.title_fragment_info1))
        })
        val pos2 = LatLng(-3.105959818945006, -79.90009364814615)
        mMap.addMarker(MarkerOptions().apply {
            position(pos2)
            title(getString(R.string.title_fragment_info2))
            contentDescription(getString(R.string.title_fragment_info2))
        })
        val pos3 = LatLng(-3.2535029218247113, -79.81199675054458)
        mMap.addMarker(MarkerOptions().apply {
            position(pos3)
            title(getString(R.string.title_fragment_info3))
            contentDescription(getString(R.string.title_fragment_info3))
        })
        val pos4 = LatLng(-3.178280150740988, -79.75605126084874)
        mMap.addMarker(MarkerOptions().apply {
            position(pos4)
            title(getString(R.string.title_fragment_info4))
            contentDescription(getString(R.string.title_fragment_info4))
        })
        val pos5 = LatLng(-3.188914657907403, -79.74282166627961)
        mMap.addMarker(MarkerOptions().apply {
            position(pos5)
            title(getString(R.string.title_fragment_info5))
            contentDescription(getString(R.string.title_fragment_info5))
        })
        //Evento que muestra la info del marcador seleccionado
        mMap.setOnMarkerClickListener {
            it.showInfoWindow()
            true
        }
        //Evento que muestra un fragment de la info seleccionada
        mMap.setOnInfoWindowClickListener {
            when(it.position){
                pos1 -> {
                    viewModel.setTitle(getString(R.string.title_fragment_info1))
                    viewModel.setImageUrl("https://lh5.googleusercontent.com/p/AF1QipNAQ69mtMhPMI_GGNMvnntmWWsHGBtl52UFdSn6=w426-h240-k-no")
                    viewModel.setDescription("Cascadas de Manuel ofrece un amplio espacio de distracción en medio de la naturaleza en donde se puede disfrutar en familia y amigos.")
                }
                pos2 -> {
                    viewModel.setTitle(getString(R.string.title_fragment_info2))
                    viewModel.setImageUrl("https://lh5.googleusercontent.com/p/AF1QipODLcEKlak0WRjM4hGCW7oBqhzhf5O5Yck18mHc=w426-h240-k-no")
                    viewModel.setDescription("La playa es lugar muy concurrido actualmente, por lo que tiene ciertas virtudes como la facilidad de acceso a comida a precios accesibles y de calidad, la variedad de actividades que se pueden desarrollar en el mar y la confianza de un lugar seguro.")
                }
                pos3 -> {
                    viewModel.setTitle(getString(R.string.title_fragment_info3))
                    viewModel.setImageUrl("https://lh5.googleusercontent.com/p/AF1QipNE9CMLM10AGRf6GfJa6xI1D5km3QK-KOgupmcP=w408-h306-k-no")
                    viewModel.setDescription("Buenos paisajes, tiene 2 piscinas, una para adultos y otra para niños con tobogan, cerca hay una pista de moto cross.")
                }
                pos4 -> {
                    viewModel.setTitle(getString(R.string.title_fragment_info4))
                    viewModel.setImageUrl("https://lh5.googleusercontent.com/p/AF1QipM4H0LTypOklEtzN1SeqJdtP5mRvXNrciVRISkM=w494-h240-k-no")
                    viewModel.setDescription("Es muy bonito tiene cascadas y el agua es natural, ademas es un lugar para tu descanso, contamos con cabañas, piscina, río, restaurant, senderos ecológicos y más.")
                }
                pos5 -> {
                    viewModel.setTitle(getString(R.string.title_fragment_info5))
                    viewModel.setImageUrl("https://lh5.googleusercontent.com/p/AF1QipN09ponHM_DgECvkVhbM38edCgLUF-cgFzgqQnj=w408-h306-k-no")
                    viewModel.setDescription("Única piscina de agua 100% natural libre de químicos, proveniente de la vertiente de la montaña.")
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