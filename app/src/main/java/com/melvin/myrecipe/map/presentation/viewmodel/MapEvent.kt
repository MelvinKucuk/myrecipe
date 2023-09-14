package com.melvin.myrecipe.map.presentation.viewmodel

sealed class MapEvent {
    object OnBackClicked : MapEvent()
    object OnUiEvenHandled : MapEvent()
}
