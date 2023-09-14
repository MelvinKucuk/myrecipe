package com.melvin.myrecipe.map.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.melvin.myrecipe.Routes
import javax.inject.Inject

class MapViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(MapState())
        private set

    init {
        val latitudeString = savedStateHandle.get<String>(Routes.MapScreen.LATITUDE)
        val longitudeString = savedStateHandle.get<String>(Routes.MapScreen.LONGITUDE)

        val latitude = latitudeString?.toDoubleOrNull()
        val longitude = longitudeString?.toDoubleOrNull()

        state = if (latitude != null && longitude != null) {
            state.copy(
                latitude = latitude,
                longitude = longitude
            )
        } else {
            state.copy(uiEvent = MapUiEvent.ShowError("invalid arguments"))
        }
    }

    fun onEvent(event: MapEvent) {
        state = when (event) {
            MapEvent.OnBackClicked -> state.copy(uiEvent = MapUiEvent.NavigateBack)
            MapEvent.OnUiEvenHandled -> state.copy(uiEvent = null)
        }
    }
}