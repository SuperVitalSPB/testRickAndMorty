package com.supervital.rickandmorty.feature.mainlist

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supervital.rickandmorty.models.CharacterInfo
import com.supervital.rickandmorty.usecase.CharacterGetListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val characterGetListUseCase: CharacterGetListUseCase
) : ViewModel() {

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    private val _items = mutableStateListOf<CharacterInfo>()
    val items: List<CharacterInfo> = _items
    private var maxPages = 1
    private var currentPage = 1
    var isLoading = false

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun findCharacters(filterParam: String, searchText: String) {
        Log.d(TAG, "filterParam = $filterParam")
        Log.d(TAG, "searchText = $searchText")
        viewModelScope.launch {

        }
    }

    suspend fun loadCharacters() {
        if (isLoading || currentPage > maxPages) return
        isLoading = true
        val data = characterGetListUseCase(currentPage)
        maxPages = data.info.pages
        _items.addAll(data.characters)
        isLoading = false
        currentPage++
    }

    companion object {
        const val TAG = "charTest:MainListViewModel"
    }
}