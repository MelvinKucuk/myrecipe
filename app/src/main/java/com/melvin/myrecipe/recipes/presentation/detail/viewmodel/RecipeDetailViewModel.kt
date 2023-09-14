package com.melvin.myrecipe.recipes.presentation.detail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.myrecipe.Routes
import com.melvin.myrecipe.core.data.Resource
import com.melvin.myrecipe.recipes.domain.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    repository: RecipeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(RecipeDetailState())
        private set

    init {
        state = state.copy(isLoading = true)

        val recipeId = savedStateHandle.get<Int>(Routes.DetailScreen.RECIPE_ID)

        if (recipeId == null) {
            state = state.copy(
                uiEvent = RecipeDetailUiEvent.ShowError("Invalid recipe id"),
                isLoading = false
            )
        }

        viewModelScope.launch {
            state = when (val result = repository.getRecipeById(recipeId!!)) {
                is Resource.Success -> state.copy(
                    recipe = result.data,
                    isLoading = false
                )

                is Resource.Error ->
                    state.copy(
                        uiEvent = RecipeDetailUiEvent.ShowError(result.errorMessage),
                        isLoading = false
                    )
            }
        }
    }

    fun onEvent(event: RecipeDetailEvent) {
        state = when (event) {
            RecipeDetailEvent.OnBackClicked ->
                state.copy(uiEvent = RecipeDetailUiEvent.NavigateBack)

            is RecipeDetailEvent.OnSeeLocationClicked ->
                state.copy(
                    uiEvent = RecipeDetailUiEvent.NavigateToMap(
                        latitude = event.latitude,
                        longitude = event.longitude
                    )
                )

            RecipeDetailEvent.OnUiEventHandled -> state.copy(uiEvent = null)
        }
    }
}