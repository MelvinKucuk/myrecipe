package com.melvin.myrecipe

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.melvin.myrecipe.recipes.presentation.detail.RecipeDetailScreen
import com.melvin.myrecipe.recipes.presentation.detail.viewmodel.RecipeDetailEvent
import com.melvin.myrecipe.recipes.presentation.detail.viewmodel.RecipeDetailUiEvent
import com.melvin.myrecipe.recipes.presentation.detail.viewmodel.RecipeDetailViewModel
import com.melvin.myrecipe.recipes.presentation.home.HomeScreen
import com.melvin.myrecipe.recipes.presentation.home.viewmodel.HomeEvent
import com.melvin.myrecipe.recipes.presentation.home.viewmodel.HomeUiEvent
import com.melvin.myrecipe.recipes.presentation.home.viewmodel.HomeViewModel
import com.melvin.tvseries.core.util.makeToast

@Composable
fun Navigation(
    navController: NavHostController,
    context: Context
) {
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        composable(Routes.HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()

            with(viewModel.state) {
                LaunchedEffect(uiEvent) {
                    when (uiEvent) {
                        is HomeUiEvent.NavigateToDetail ->
                            navController.navigate(
                                Routes.DetailScreen.getDestination(uiEvent.recipeId)
                            )

                        is HomeUiEvent.ShowError -> makeToast(context, uiEvent.errorMessage)
                        else -> {}
                    }
                    viewModel.onEvent(HomeEvent.OnUiEventHandled)
                }
            }

            HomeScreen(state = viewModel.state, onEvent = viewModel::onEvent)
        }

        composable(
            route = Routes.DetailScreen.getCompleteRoute(),
            arguments = listOf(
                navArgument(Routes.DetailScreen.RECIPE_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            val viewModel: RecipeDetailViewModel = hiltViewModel()

            with(viewModel.state) {
                LaunchedEffect(uiEvent) {
                    when (uiEvent) {
                        RecipeDetailUiEvent.NavigateBack -> navController.popBackStack()
                        is RecipeDetailUiEvent.NavigateToMap -> navController.popBackStack() //TODO
                        is RecipeDetailUiEvent.ShowError -> makeToast(context, uiEvent.errorMessage)
                        null -> {}
                    }
                    viewModel.onEvent(RecipeDetailEvent.OnUiEventHandled)
                }
            }

            RecipeDetailScreen(state = viewModel.state, onEvent = viewModel::onEvent)
        }
    }
}