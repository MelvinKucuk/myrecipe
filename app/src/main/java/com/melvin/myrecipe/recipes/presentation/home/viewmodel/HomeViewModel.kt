package com.melvin.myrecipe.recipes.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.myrecipe.core.data.Resource
import com.melvin.myrecipe.recipes.domain.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: RecipeRepository
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            state = when (val result = repository.getRecipes()) {
                is Resource.Success -> {
                    state.copy(recipes = result.data)
                }

                is Resource.Error ->
                    state.copy(uiEvent = HomeUiEvent.ShowError(result.errorMessage))
            }

        }
    }

    fun onEvent(event: HomeEvent) {
        state = when (event) {
            is HomeEvent.OnRecipeClick -> {
                state.copy(uiEvent = HomeUiEvent.NavigateToDetail(event.recipeId))
            }

            is HomeEvent.OnTextChanged -> state.copy(searchText = event.text)
        }
    }
}