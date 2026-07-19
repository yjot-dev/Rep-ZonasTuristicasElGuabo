package com.yjotdev.zonasturisticaselguabo.presentation.navigation

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.yjotdev.zonasturisticaselguabo.MainActivity
import com.yjotdev.zonasturisticaselguabo.R

fun MainActivity.setupAppPermissions() {
    val context = this@setupAppPermissions
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
    val navHostFragment = supportFragmentManager
        .findFragmentById(R.id.fragmentNav) as NavHostFragment
    val navController = navHostFragment.navController
    // 1. Obtenemos el grafo
    val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
    // 2. Aplicamos el grafo al controlador
    navController.graph = navGraph
}