package com.supervital.rickandmorty.feature.details

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.supervital.rickandmorty.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopAppBarScreen(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
): Unit = TopAppBar(
    modifier = modifier,
    title = { Text(text = LocalContext.current.getString(R.string.title_detail))},
    navigationIcon = {
        if (canNavigateBack) {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "back_button"
                )
            }
        }
    },
    colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Black, // Цвет фона панели
        titleContentColor = Color.White, // Цвет текста заголовка
        navigationIconContentColor = Color.White, // Цвет иконки навигации
        actionIconContentColor = Color.White // Цвет иконок действий
    )
)