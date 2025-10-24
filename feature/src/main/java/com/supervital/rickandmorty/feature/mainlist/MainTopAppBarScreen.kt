package com.supervital.rickandmorty.feature.mainlist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.supervital.rickandmorty.R.string

@Composable
fun MainTopAppBarScreen(viewModel: MainListViewModel = hiltViewModel()) {
    when (viewModel.searchWidgetState.value) {
        SearchWidgetState.CLOSED -> {
            ClosedAppBar (
                onSearchClicked = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        }
        SearchWidgetState.OPENED -> {
            OpenedAppBar(
                text = viewModel.searchTextState.value,
                onTextChange =  { viewModel.updateSearchTextState(newValue = it) },
                onCloseClicked = { viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED) },
                onSearchClicked = { filterParam, searchText -> viewModel.filterCharacters(filterParam, searchText) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClosedAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = string.prompt_search),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onSearchClicked() }),
                style = MaterialTheme.typography.titleSmall
            )
        },
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "SearchIcon",
                )

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black, // Цвет фона панели
            titleContentColor = Color.Gray, // Цвет текста заголовка
            actionIconContentColor = Color.Gray // Цвет иконок действий
        )
    )
}

const val TAGs = "charTest:OpenedAppBar"

@Composable
fun OpenedAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String, String) -> Unit,
) {
    var isEnterFilter by remember { mutableStateOf(false) }
    var filterParam by remember { mutableStateOf("") }

    RadioButtonsPopup(filterParam = { inFilterParam ->
        filterParam = inFilterParam
        Log.d(TAGs, "filterParam = $filterParam")
        isEnterFilter = filterParam.isNotEmpty()
        if (!isEnterFilter) {
            onCloseClicked()
        }
    })

    if (isEnterFilter) {
        val focusRequester = remember { FocusRequester() }
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.Black),
            color = Color.White
        ) {
            TextField(
                modifier = Modifier
                    .background(Color.Black)
                    .focusRequester(focusRequester)
                    .fillMaxWidth(),
                value = text,
                onValueChange = {
                    onTextChange(it)
                },
                placeholder = {
                    Text(
                        text = stringResource(string.prompt_enter_search),
                        color = Color.Black
                    )
                },
                textStyle = TextStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                ),
                singleLine = true,
                leadingIcon = {
                    IconButton(
                        onClick = { onSearchClicked(filterParam, text) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.White
                        )

                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (text.isNotEmpty()) {
                                onTextChange("")
                            } else {
                                onCloseClicked()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon",
                            tint = Color.White
                        )

                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    capitalization = KeyboardCapitalization.None
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(filterParam, text)
                    }
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Black,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    disabledContainerColor = Color.Black,
                    focusedContainerColor = Color.Black
                )
            )
        }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}