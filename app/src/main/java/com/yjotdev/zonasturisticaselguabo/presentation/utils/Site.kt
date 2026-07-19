package com.yjotdev.zonasturisticaselguabo.presentation.utils

import com.google.android.gms.maps.model.LatLng

data class Site(
    val titleId: Int,
    val imageUrl: String,
    val descriptionId: Int,
    val position: LatLng
)