@file:OptIn(ExperimentalMaterial3Api::class)

package com.melvin.myrecipe.recipes.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.melvin.myrecipe.R
import com.melvin.myrecipe.ui.theme.Purple40

@Composable
fun SearchHeader(
    text: String,
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Purple40),
        contentAlignment = Alignment.Center,
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            placeholder = {
                Text(text = stringResource(R.string.search_placeholder))
            },
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                containerColor = Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                placeholderColor = Color.Gray
            ),
            shape = CircleShape,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search)
                )
            }
        )
    }
}

@Preview
@Composable
fun SearchHeaderPreview() {
    SearchHeader(text = "") {}
}