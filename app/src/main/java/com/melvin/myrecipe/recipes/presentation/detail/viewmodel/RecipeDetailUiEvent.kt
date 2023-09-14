package com.melvin.myrecipe.recipes.presentation.detail.viewmodel

sealed class RecipeDetailUiEvent {
    object NavigateBack : RecipeDetailUiEvent()
    data class ShowError(val errorMessage: String) : RecipeDetailUiEvent()
    data class NavigateToMap(
        val latitude: Double,
        val longitude: Double
    ) : RecipeDetailUiEvent()
}
