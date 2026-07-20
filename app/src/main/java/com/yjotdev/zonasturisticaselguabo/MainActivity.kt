package com.yjotdev.zonasturisticaselguabo

import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yjotdev.zonasturisticaselguabo.presentation.navigation.setupPermissions
import com.yjotdev.zonasturisticaselguabo.databinding.ActivityMainBinding
import com.yjotdev.zonasturisticaselguabo.presentation.navigation.setupNavigation

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    internal lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configura la IU de la actividad
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Configura la navegación, toolbar y edge-to-edge
        setupNavigation()
        // solicita permisos necesarios
        setupPermissions()
    }
}