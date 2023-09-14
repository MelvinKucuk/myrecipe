package com.melvin.myrecipe.recipes.presentation.home.viewmodel

sealed class HomeUiEvent {
    data class NavigateToDetail(val recipeId: Int) : HomeUiEvent()
    data class ShowError(val errorMessage: String) : HomeUiEvent()
}
