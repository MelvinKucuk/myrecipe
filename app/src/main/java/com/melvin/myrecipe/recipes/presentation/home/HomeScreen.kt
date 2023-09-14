package com.melvin.myrecipe.recipes.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.melvin.myrecipe.recipes.domain.Recipe
import com.melvin.myrecipe.recipes.presentation.home.components.RecipeCard
import com.melvin.myrecipe.recipes.presentation.home.components.SearchHeader
import com.melvin.myrecipe.recipes.presentation.home.viewmodel.HomeEvent
import com.melvin.myrecipe.recipes.presentation.home.viewmodel.HomeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    Scaffold(
        topBar = {
            SearchHeader(text = state.searchText) {
                onEvent(HomeEvent.OnTextChanged(it))
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(state.recipes) { recipe ->
                RecipeCard(title = recipe.name, image = recipe.image) {
                    onEvent(HomeEvent.OnRecipeClick(recipe.id))
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState(
            recipes = listOf(
                Recipe(
                    name = "Chicken Stroganoff",
                    image = "url"
                ),
                Recipe(
                    name = "Chicken",
                    image = "url"
                ),
                Recipe(
                    name = "Ribeye",
                    image = "url"
                )
            )
        )
    ) {}
}