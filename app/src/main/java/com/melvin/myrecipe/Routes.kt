package com.melvin.myrecipe

sealed class Routes(val route: String) {
    object HomeScreen : Routes("home_screen")
    object DetailScreen : Routes("detail_screen") {
        fun getCompleteRoute() = "$route?$RECIPE_ID={$RECIPE_ID}"
        fun getDestination(recipeId: Int) = "$route?$RECIPE_ID=$recipeId"


        const val RECIPE_ID = "recipe_id"
    }

    object MapScreen : Routes("map_screen") {
        fun getCompleteRoute() = "$route?$LONGITUDE={$LONGITUDE}&$LATITUDE={$LATITUDE}"
        fun getDestination(
            longitude: Double,
            latitude: Double,
        ) = "$route?$LONGITUDE=$longitude&$LATITUDE=$latitude"


        const val LONGITUDE = "longitude"
        const val LATITUDE = "latitude"
    }
}
