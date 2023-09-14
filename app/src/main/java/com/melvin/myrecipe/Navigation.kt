package com.melvin.myrecipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.melvin.myrecipe.recipes.presentation.home.HomeScreen
import com.melvin.myrecipe.recipes.presentation.home.viewmodel.HomeUiEvent
import com.melvin.myrecipe.recipes.presentation.home.viewmodel.HomeViewModel
import com.melvin.tvseries.core.util.makeToast

@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        composable(Routes.HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val context = LocalContext.current

            with(viewModel.state) {
                LaunchedEffect(uiEvent) {
                    when (uiEvent) {
                        is HomeUiEvent.NavigateToDetail -> {}//TODO
                        is HomeUiEvent.ShowError -> makeToast(context, uiEvent.errorMessage)
                        else -> {}
                    }
                }
            }

            HomeScreen(state = viewModel.state, onEvent = viewModel::onEvent)
        }
    }
}