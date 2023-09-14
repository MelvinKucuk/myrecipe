package com.melvin.myrecipe.recipes.domain

import com.melvin.myrecipe.core.data.Resource

interface RecipeRepository {
    suspend fun getRecipes(): Resource<List<Recipe>>
}