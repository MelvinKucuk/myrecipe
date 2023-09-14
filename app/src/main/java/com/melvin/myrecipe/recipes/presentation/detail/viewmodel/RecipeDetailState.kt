package com.melvin.myrecipe.recipes.presentation.detail.viewmodel

import com.melvin.myrecipe.recipes.domain.Recipe

data class RecipeDetailState(
    val recipe: Recipe = Recipe(),
    val isLoading: Boolean = true,
    val uiEvent: RecipeDetailUiEvent? = null
)
