package com.melvin.myrecipe.recipes.domain

data class Recipe(
    val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val ingredients: List<String> = listOf(),
    val description: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
