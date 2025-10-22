package com.supervital.rickandmorty.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class Status(val statusString: String, val colorPunkt: Color,val widthBox: Dp) {
    ALIVE("Alive", Color.Green, 80.dp), // позор моим сединам, разобраться
    DEAD("Dead", Color.Gray, 80.dp),
    UNKNOWN("unknown", Color.Cyan, 110.dp)
}