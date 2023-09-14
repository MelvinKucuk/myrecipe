package com.melvin.myrecipe.recipes.presentation.home.viewmodel

import com.melvin.myrecipe.MainCoroutineRule
import com.melvin.myrecipe.errorRecipe
import com.melvin.myrecipe.recipes
import com.melvin.myrecipe.recipes.domain.RecipeRepository
import com.melvin.myrecipe.successfulRecipe
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val repository: RecipeRepository = mockk()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        coEvery { repository.getRecipes() } returns successfulRecipe

        viewModel = HomeViewModel(repository)

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()
    }

    @Test
    fun `validate viewmodel init with successful response`() {
        coEvery { repository.getRecipes() } returns successfulRecipe

        viewModel = HomeViewModel(repository)

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertEquals(recipes, viewModel.state.recipes)
        assertEquals(recipes, viewModel.state.allRecipes)
    }

    @Test
    fun `validate viewmodel init with error response`() {
        coEvery { repository.getRecipes() } returns errorRecipe

        viewModel = HomeViewModel(repository)

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertEquals(HomeUiEvent.ShowError("error"), viewModel.state.uiEvent)
    }

    @Test
    fun `validate text state changed on event OnTextChanged`() {
        val expectedValue = "carrot"
        viewModel.onEvent(HomeEvent.OnTextChanged(expectedValue))

        assertEquals(expectedValue, viewModel.state.searchText)
    }

    @Test
    fun `validate search applied on event OnTextChanged on recipe name`() {
        val expectedValue = "rib"
        viewModel.onEvent(HomeEvent.OnTextChanged(expectedValue))

        assertTrue(
            viewModel.state.recipes.any { it.name.lowercase().contains(expectedValue.lowercase()) }
        )
    }

    @Test
    fun `validate search applied on event OnTextChanged on ingredient`() {
        val expectedValue = "carr"
        viewModel.onEvent(HomeEvent.OnTextChanged(expectedValue))

        assertTrue(
            viewModel.state.recipes.any {
                it.ingredients.any { ingredient ->
                    ingredient.lowercase().contains(expectedValue.lowercase())
                }
            }
        )
    }

    @Test
    fun `validate search applied on event OnTextChanged on recipe name with uppercase`() {
        val expectedValue = "RIB"
        viewModel.onEvent(HomeEvent.OnTextChanged(expectedValue))

        assertTrue(
            viewModel.state.recipes.any { it.name.lowercase().contains(expectedValue.lowercase()) }
        )
    }

    @Test
    fun `validate event OnRecipeClick`() {
        val recipeId = 1
        viewModel.onEvent(HomeEvent.OnRecipeClick(recipeId))

        assertEquals(HomeUiEvent.NavigateToDetail(recipeId), viewModel.state.uiEvent)
    }
}