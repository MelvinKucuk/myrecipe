package com.melvin.myrecipe.recipes.di

import com.melvin.myrecipe.recipes.data.RecipeRepositoryImpl
import com.melvin.myrecipe.recipes.data.RecipeService
import com.melvin.myrecipe.recipes.domain.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
class RecipesModule {

    @Provides
    fun provideRecipeService(retrofit: Retrofit): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsRecipeModule {

    @Binds
    abstract fun providesRecipeRepository(repositoryImpl: RecipeRepositoryImpl): RecipeRepository
}