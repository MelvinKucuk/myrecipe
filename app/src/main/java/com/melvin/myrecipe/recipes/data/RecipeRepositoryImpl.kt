package com.melvin.myrecipe.recipes.data

import com.melvin.myrecipe.core.data.Resource
import com.melvin.myrecipe.core.data.safeApiCall
import com.melvin.myrecipe.recipes.data.model.toDomain
import com.melvin.myrecipe.recipes.domain.Recipe
import com.melvin.myrecipe.recipes.domain.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val service: RecipeService
) : RecipeRepository {

    override suspend fun getRecipes(): Resource<List<Recipe>> {
        val result = safeApiCall {
            service.getRecipes()
        }

        return when (result) {
            is Resource.Success -> Resource.Success(result.data.map { it.toDomain() })
            is Resource.Error -> Resource.Error(result.errorMessage)
        }
    }

    override suspend fun getRecipeById(recipeId: Int): Resource<Recipe> {
        val result = safeApiCall {
            service.getRecipeById(recipeId)
        }

        return when (result) {
            is Resource.Success -> Resource.Success(result.data.toDomain())
            is Resource.Error -> Resource.Error(result.errorMessage)
        }
    }
}