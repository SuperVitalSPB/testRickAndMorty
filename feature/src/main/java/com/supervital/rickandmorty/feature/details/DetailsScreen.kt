package com.supervital.rickandmorty.feature.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun DetailsScreen(
    characterInfoId: Int,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    Text(text = characterInfoId.toString())
}