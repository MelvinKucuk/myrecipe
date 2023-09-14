package com.melvin.myrecipe

import com.melvin.myrecipe.core.data.Resource
import com.melvin.myrecipe.recipes.domain.Recipe

val recipes = listOf(
    Recipe(
        id = 1,
        name = "Ribeye",
        image = "url",
        ingredients = listOf("beef", "garlic"),
        description = "This a great recipe",
        latitude = 1.0,
        longitude = 2.0
    ),
    Recipe(
        id = 1,
        name = "Vegetal soup",
        image = "url",
        ingredients = listOf("carrot", "spinach"),
        description = "This a great recipe",
        latitude = 1.0,
        longitude = 2.0
    ),
)

val successfulRecipe = Resource.Success(recipes)

val errorRecipe = Resource.Error<List<Recipe>>("error")