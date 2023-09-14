package com.melvin.myrecipe.recipes.data.model

import com.squareup.moshi.Json

data class RecipeDto(
    @Json(name = "id") val id: Int?,
    @Json(name = "image") val image: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "ingredients") val ingredients: List<String>?,
    @Json(name = "latitude") val latitude: Double?,
    @Json(name = "longitude") val longitude: Double?,
)
