package com.melvin.myrecipe.recipes.presentation.detail.viewmodel

sealed class RecipeDetailEvent {
    object OnBackClicked : RecipeDetailEvent()
    data class OnSeeLocationClicked(
        val latitude: Double,
        val longitude: Double
    ) : RecipeDetailEvent()

    object OnUiEventHandled : RecipeDetailEvent()
}
