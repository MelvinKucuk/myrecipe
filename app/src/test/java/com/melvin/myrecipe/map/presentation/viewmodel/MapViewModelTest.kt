package com.melvin.myrecipe.map.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class MapViewModelTest {

    private val savedStateHandle: SavedStateHandle = mockk()

    private lateinit var viewModel: MapViewModel

    @Before
    fun setUp() {
        every { savedStateHandle.get<String>(any()) } returns "1.2"

        viewModel = MapViewModel(savedStateHandle)
    }

    @Test
    fun `validate correct init with valid arguments of viewmodel`() {
        val expectedValueString = "1.2"
        val expectedValue = 1.2
        every { savedStateHandle.get<String>(any()) } returns expectedValueString

        viewModel = MapViewModel(savedStateHandle)

        assertEquals(expectedValue, viewModel.state.latitude)
        assertEquals(expectedValue, viewModel.state.longitude)
    }

    @Test
    fun `validate error on init with invalid arguments of viewmodel`() {
        val expectedValueString = "1.2e"
        every { savedStateHandle.get<String>(any()) } returns expectedValueString

        viewModel = MapViewModel(savedStateHandle)

        assertEquals(MapUiEvent.ShowError("invalid arguments"), viewModel.state.uiEvent)
    }

    @Test
    fun `validate event OnUiEvenHandled`() {
        viewModel.onEvent(MapEvent.OnUiEvenHandled)

        assertEquals(null, viewModel.state.uiEvent)
    }

    @Test
    fun `validate event OnBackClicked`() {
        viewModel.onEvent(MapEvent.OnBackClicked)

        assertEquals(MapUiEvent.NavigateBack, viewModel.state.uiEvent)
    }
}