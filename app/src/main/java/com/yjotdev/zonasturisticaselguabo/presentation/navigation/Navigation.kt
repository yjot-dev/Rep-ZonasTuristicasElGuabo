package com.yjotdev.zonasturisticaselguabo.presentation.navigation

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.util.TypedValue
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.yjotdev.zonasturisticaselguabo.MainActivity
import com.yjotdev.zonasturisticaselguabo.R

fun MainActivity.setupPermissions() {
    val context = this@setupPermissions
    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(context, context.getString(R.string.toast_permission_granted), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, context.getString(R.string.toast_permission_denied), Toast.LENGTH_LONG).show()
        }
    }
    if (PackageManager.PERMISSION_GRANTED ==
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)){
        Toast.makeText(context, context.getString(R.string.toast_permission_1), Toast.LENGTH_SHORT).show()
    }else{
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
    if (PackageManager.PERMISSION_GRANTED ==
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)){
        Toast.makeText(context, context.getString(R.string.toast_permission_2), Toast.LENGTH_SHORT).show()
    }else{
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }
}

fun MainActivity.setupNavigation() {
    // 1. Configurar Edge-to-Edge e Insets (Unificado)
    enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
        navigationBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
    )
    ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        // Padding inferior y lateral para el contenedor raíz
        v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
        // Padding superior para el Toolbar (para que no lo tape la barra de estado)
        binding.includeToolbar.toolbar.setPadding(0, systemBars.top, 0, 0)
        // Ajustar altura del Toolbar para que quepa el padding + el contenido
        val tv = TypedValue()
        var actionBarHeight = 0
        if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
        }
        binding.includeToolbar.toolbar.layoutParams.height = systemBars.top + actionBarHeight
        insets
    }
    // 2. Configurar el NavController
    val navHostFragment = supportFragmentManager
        .findFragmentById(R.id.fragmentNav) as NavHostFragment
    val navController = navHostFragment.navController
    // 3. Vincular Toolbar con Navigation
    val appBarConfiguration = AppBarConfiguration(navController.graph)
    binding.includeToolbar.toolbar.setupWithNavController(navController, appBarConfiguration)
}