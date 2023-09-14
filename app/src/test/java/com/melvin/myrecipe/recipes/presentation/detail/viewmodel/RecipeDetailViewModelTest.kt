package com.melvin.myrecipe.recipes.presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.melvin.myrecipe.MainCoroutineRule
import com.melvin.myrecipe.errorRecipe
import com.melvin.myrecipe.recipe
import com.melvin.myrecipe.recipes.domain.RecipeRepository
import com.melvin.myrecipe.successfulRecipe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RecipeDetailViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val repository: RecipeRepository = mockk()

    private val savedStateHandle: SavedStateHandle = mockk()

    private lateinit var viewModel: RecipeDetailViewModel

    @Before
    fun setUp() {
        coEvery { repository.getRecipeById(any()) } returns successfulRecipe

        every { savedStateHandle.get<Int>(any()) } returns 1

        viewModel = RecipeDetailViewModel(
            repository = repository,
            savedStateHandle = savedStateHandle
        )

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()
    }

    @Test
    fun `validate viewmodel init with successful response`() {
        assertEquals(recipe, viewModel.state.recipe)
        assertFalse(viewModel.state.isLoading)
    }

    @Test
    fun `validate viewmodel init with error response`() {
        coEvery { repository.getRecipeById(any()) } returns errorRecipe

        viewModel = RecipeDetailViewModel(
            repository = repository,
            savedStateHandle = savedStateHandle
        )

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertEquals(RecipeDetailUiEvent.ShowError("error"), viewModel.state.uiEvent)
        assertFalse(viewModel.state.isLoading)
    }

    @Test
    fun `validate viewmodel init with invalid arguments`() {
        every { savedStateHandle.get<Int>(any()) } returns null

        viewModel = RecipeDetailViewModel(
            repository = repository,
            savedStateHandle = savedStateHandle
        )

        assertEquals(RecipeDetailUiEvent.ShowError("Invalid recipe id"), viewModel.state.uiEvent)
        assertFalse(viewModel.state.isLoading)
    }

    @Test
    fun `validate viewmodel event OnBackClicked`() {
        viewModel.onEvent(RecipeDetailEvent.OnBackClicked)

        assertEquals(RecipeDetailUiEvent.NavigateBack, viewModel.state.uiEvent)
    }

    @Test
    fun `validate viewmodel event OnUiEventHandled`() {
        viewModel.onEvent(RecipeDetailEvent.OnUiEventHandled)

        assertEquals(null, viewModel.state.uiEvent)
    }

    @Test
    fun `validate viewmodel event OnSeeLocationClicked`() {
        val expectedLatitude = 1.0
        val expectedLongitude = 1.0

        viewModel.onEvent(
            RecipeDetailEvent.OnSeeLocationClicked(
                latitude = expectedLatitude,
                longitude = expectedLongitude
            )
        )

        assertEquals(
            RecipeDetailUiEvent.NavigateToMap(
                latitude = expectedLatitude,
                longitude = expectedLongitude
            ),
            viewModel.state.uiEvent
        )
    }
}