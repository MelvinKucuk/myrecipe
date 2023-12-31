package com.melvin.myrecipe.recipes.presentation.home.viewmodel

import com.melvin.myrecipe.recipes.domain.Recipe

data class HomeState(
    val allRecipes: List<Recipe> = listOf(),
    val recipes: List<Recipe> = listOf(),
    val searchText: String = "",
    val isLoading: Boolean = true,
    val uiEvent: HomeUiEvent? = null
)
