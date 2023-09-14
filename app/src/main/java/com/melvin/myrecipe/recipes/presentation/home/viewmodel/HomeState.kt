package com.melvin.myrecipe.recipes.presentation.home.viewmodel

import com.melvin.myrecipe.recipes.domain.Recipe

data class HomeState(
    val recipes: List<Recipe> = listOf(),
    val searchText: String = "",
    val uiEvent: HomeUiEvent? = null
)
