package com.melvin.myrecipe.map.presentation

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.melvin.myrecipe.R
import com.melvin.myrecipe.map.presentation.viewmodel.MapEvent
import com.melvin.myrecipe.map.presentation.viewmodel.MapState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    state: MapState,
    onEvent: (MapEvent) -> Unit
) {
    val multiplePermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(state.latitude, state.longitude), 17f)
    }

    LaunchedEffect(Unit) {
        multiplePermissionState.launchMultiplePermissionRequest()
    }

    if (multiplePermissionState.allPermissionsGranted) {
        Box {
            GoogleMap(
                cameraPositionState = cameraPositionState,
                modifier = Modifier.fillMaxSize(),
                properties = MapProperties(isMyLocationEnabled = true),
                uiSettings = MapUiSettings(compassEnabled = true)
            ) {
                GoogleMarkers(
                    latitude = state.latitude,
                    longitude = state.longitude
                )
            }

            IconButton(
                modifier = Modifier.padding(8.dp),
                onClick = { onEvent(MapEvent.OnBackClicked) }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(
                        id = R.string.back
                    )
                )
            }
        }
    }
}

@Composable
fun GoogleMarkers(
    latitude: Double,
    longitude: Double
) {
    Marker(
        state = rememberMarkerState(position = LatLng(latitude, longitude)),
        title = "Marker1",
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
    )
}

@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(state = MapState()) {}
}