package com.melvin.myrecipe.recipes.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.melvin.myrecipe.R
import com.melvin.myrecipe.recipes.domain.Recipe
import com.melvin.myrecipe.recipes.presentation.detail.viewmodel.RecipeDetailEvent
import com.melvin.myrecipe.recipes.presentation.detail.viewmodel.RecipeDetailState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onEvent: (RecipeDetailEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.recipe.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onEvent(RecipeDetailEvent.OnBackClicked) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.back
                            )
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.recipe.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.image),
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.placeholder),
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.size(12.dp))

                Column(modifier = Modifier.padding(horizontal = 24.dp)) {


                    Text(
                        text = state.recipe.description
                    )

                    Spacer(modifier = Modifier.size(12.dp))

                    Text(
                        text = stringResource(R.string.ingredients)
                    )

                    Spacer(Modifier.size(8.dp))

                    state.recipe.ingredients.forEach {
                        Row {
                            Icon(imageVector = Icons.Default.Done, contentDescription = null)

                            Spacer(Modifier.size(8.dp))

                            Text(
                                text = it
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(12.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onEvent(
                                RecipeDetailEvent.OnSeeLocationClicked(
                                    latitude = state.recipe.latitude,
                                    longitude = state.recipe.longitude
                                )
                            )
                        }) {
                        Text(text = stringResource(R.string.see_location))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeDetailScreenRecipe() {
    RecipeDetailScreen(
        state = RecipeDetailState(
            isLoading = false,
            recipe = Recipe(
                id = 1,
                name = "Ribeye",
                image = "url",
                ingredients = listOf("beef", "garlic"),
                description = "This a great recipe",
                latitude = 1.0,
                longitude = 2.0
            )
        )
    ) {}
}