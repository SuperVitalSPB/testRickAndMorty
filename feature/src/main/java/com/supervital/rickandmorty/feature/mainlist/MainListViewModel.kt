package com.supervital.rickandmorty.feature.mainlist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supervital.rickandmorty.models.CharacterInfo
import com.supervital.rickandmorty.usecase.CharacterGetListUseCase
import com.supervital.rickandmorty.usecase.CharacterSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val characterGetListUseCase: CharacterGetListUseCase,
    private val characterSearchUseCase: CharacterSearchUseCase
) : ViewModel() {

    private val _searchWidgetState: MutableState<SearchWidgetState> = mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<Pair<String, String>> = mutableStateOf(value = Pair("", ""))
    val searchTextState: State<Pair<String, String>> = _searchTextState

    private val _items = mutableStateListOf<CharacterInfo>()
    val items: List<CharacterInfo> = _items

    private var maxPages = 1
    private var currentPage = 1
    var isLoading = false
    var isNoData = mutableStateOf(value = false)

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        val paramName = _searchTextState.value.first
        _searchTextState.value = Pair(paramName, newValue)
    }

    private fun isFilterOpened() = _searchWidgetState.value == SearchWidgetState.OPENED

    private fun isFilterReady() =
        searchTextState.value.first.isNotEmpty()
                && searchTextState.value.second.isNotEmpty()
                && searchTextState.value.second.length >= MIN_LEN_SEARCH

    fun loadFilter() {
        viewModelScope.launch {
            loadCharacters()
        }
    }

    suspend fun loadCharacters() {
        if ((isLoading || (currentPage > maxPages)) && !isFilterOpened()) {
            return
        }

        isLoading = true
        isNoData.value = false

        val data = if (isFilterOpened() && isFilterReady()) {
            _items.clear()
            val result = characterSearchUseCase(
                mapOf(
                    searchTextState.value.first to searchTextState.value.second,
                    PARAM_PAGE_NAME to currentPage.toString()
                )
            )
            when {
                result.isSuccess -> result.getOrNull()
                else -> {
                    isNoData.value = true
                    null
                }
            }
        } else {
            characterGetListUseCase(currentPage)
        }
        data?.let {
            maxPages = data.info.pages
            _items.addAll(data.characters)
            currentPage++
        }
        isLoading = false
    }

    fun clearFilterParams() {
        _searchTextState.value = Pair("", "")
    }

    fun fillFilterParams(paramName: String, value: String) {
        _searchTextState.value = Pair(paramName, value)
    }

    companion object {
        const val PARAM_PAGE_NAME = "page"
        const val MIN_LEN_SEARCH = 3
    }
}