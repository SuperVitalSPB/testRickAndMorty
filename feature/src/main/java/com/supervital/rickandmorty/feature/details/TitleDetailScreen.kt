package com.supervital.rickandmorty.feature.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.supervital.rickandmorty.R

@Composable
fun TitleDetailScreen() {
    Text(text = LocalContext.current.getString(R.string.title_detail))
}
