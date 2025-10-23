package com.supervital.rickandmorty.feature.mainlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.supervital.rickandmorty.R

@Composable
fun RadioButtonsPopupExample() {
    var showDialog by remember { mutableStateOf(true) }
    var selectedOption by remember { mutableStateOf("") } // Начальное выбранное значение

    val filters = listOf( // stringArrayResource глючит
                            stringResource(R.string.filter_param_name),
                            stringResource(R.string.filter_param_status),
                            stringResource(R.string.filter_param_species),
                            stringResource(R.string.filter_param_type),
                            stringResource(R.string.filter_param_gender))

    if (showDialog) {
        AlertDialog(
            modifier = Modifier.width(220.dp),
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(R.string.filter_by)) },
            text = {
                Column {
                    filters.forEach { text ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = {
                                    selectedOption = text
                                    showDialog = false
                                }
                            )
                            Text(
                                text = text
                            )
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedOption = ""
                        showDialog = false
                    }
                ) {
                    Text(stringResource(  android.R.string.cancel))
                }
            },
        )
    }
}