package com.melvin.myrecipe

sealed class Routes(val route: String) {
    object HomeScreen : Routes("home_screen")
    object DetailScreen : Routes("detail_screen") {
        fun getCompleteRoute() = "$route?$RECIPE_ID={$RECIPE_ID}"
        fun getDestination(recipeId: Long) = "$route?$RECIPE_ID=$recipeId"


        const val RECIPE_ID = "recipe_id"
    }
}