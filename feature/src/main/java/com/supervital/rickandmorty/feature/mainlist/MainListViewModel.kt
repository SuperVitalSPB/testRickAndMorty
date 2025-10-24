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
import com.supervital.rickandmorty.usecase.CharacterSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val characterGetListUseCase: CharacterGetListUseCase,
    private val characterSearchUseCase: CharacterSearchUseCase
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

    fun filterCharacters(filterParam: String, searchText: String) {
        Log.d(TAG, "filterParam = $filterParam")
        Log.d(TAG, "searchText = $searchText")
        viewModelScope.launch {
            loadCharacters(mutableMapOf(filterParam to searchText))
        }
    }

    suspend fun loadCharacters(filterParams: Map<String, String>? = null) {
        if (isLoading || currentPage > maxPages) return
        isLoading = true

        val data = filterParams?.let {
            _items.clear()
            (filterParams as MutableMap)[PARAM_PAGE_NAME] = currentPage.toString()
            characterSearchUseCase(filterParams)
        } ?: characterGetListUseCase(currentPage)



        maxPages = data.info.pages
        _items.addAll(data.characters)
        isLoading = false
        currentPage++
    }

    companion object {
        const val TAG = "charTest:MainListViewModel"
        const val PARAM_PAGE_NAME = "page"
    }
}