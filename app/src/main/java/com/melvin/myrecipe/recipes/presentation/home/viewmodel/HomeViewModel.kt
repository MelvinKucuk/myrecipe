package com.melvin.myrecipe.recipes.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.myrecipe.core.data.Resource
import com.melvin.myrecipe.recipes.domain.Recipe
import com.melvin.myrecipe.recipes.domain.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: RecipeRepository
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            state = when (val result = repository.getRecipes()) {
                is Resource.Success -> {
                    state.copy(
                        recipes = result.data,
                        allRecipes = result.data,
                        isLoading = false
                    )
                }

                is Resource.Error ->
                    state.copy(
                        uiEvent = HomeUiEvent.ShowError(result.errorMessage),
                        isLoading = false
                    )
            }

        }
    }

    fun onEvent(event: HomeEvent) {
        state = when (event) {
            is HomeEvent.OnRecipeClick ->
                state.copy(uiEvent = HomeUiEvent.NavigateToDetail(event.recipeId))


            is HomeEvent.OnTextChanged -> {
                val searchResults = searchRecipes(state.recipes, event.text)

                state.copy(
                    searchText = event.text,
                    recipes = if (event.text.isNotBlank()) {
                        searchResults
                    } else {
                        state.allRecipes
                    }
                )
            }

            HomeEvent.OnUiEventHandled -> state.copy(uiEvent = null)
        }
    }

    private fun searchRecipes(recipes: List<Recipe>, query: String): List<Recipe> {
        val lowerCaseQuery = query.lowercase(Locale.ROOT)
        return recipes.filter { recipe ->
            recipe.name.lowercase(Locale.ROOT).contains(lowerCaseQuery) ||
                    recipe.ingredients.any { ingredient ->
                        ingredient.lowercase(Locale.ROOT).contains(lowerCaseQuery)
                    }
        }
    }
}