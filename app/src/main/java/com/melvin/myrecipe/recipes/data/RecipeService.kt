package com.melvin.myrecipe.recipes.data

import com.melvin.myrecipe.recipes.data.model.RecipeDto
import retrofit2.Response
import retrofit2.http.GET

interface RecipeService {

    @GET("/recipes")
    suspend fun getRecipes(): Response<List<RecipeDto>>
}