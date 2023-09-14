package com.melvin.myrecipe.recipes.data.model

import com.melvin.myrecipe.recipes.domain.Recipe

fun RecipeDto.toDomain() =
    Recipe(
        id = id ?: 0,
        name = name ?: "",
        image = image ?: "",
        ingredients = ingredients ?: listOf(),
        description = description ?: "",
        latitude = latitude ?: 0.0,
        longitude = longitude ?: 0.0,
    )