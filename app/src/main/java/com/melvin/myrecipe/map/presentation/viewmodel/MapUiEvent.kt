package com.melvin.myrecipe.map.presentation.viewmodel

sealed class MapUiEvent {
    object NavigateBack : MapUiEvent()
    data class ShowError(val errorMessage: String) : MapUiEvent()
}
