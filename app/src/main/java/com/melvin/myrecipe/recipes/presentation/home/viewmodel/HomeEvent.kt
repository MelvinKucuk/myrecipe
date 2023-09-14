package com.melvin.myrecipe.recipes.presentation.home.viewmodel

sealed class HomeEvent {
    data class OnTextChanged(val text: String) : HomeEvent()
    data class OnRecipeClick(val recipeId: Int) : HomeEvent()
    object OnUiEventHandled : HomeEvent()
}
