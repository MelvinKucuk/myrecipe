package com.melvin.myrecipe.map.presentation.viewmodel

data class MapState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val uiEvent: MapUiEvent? = null
)
