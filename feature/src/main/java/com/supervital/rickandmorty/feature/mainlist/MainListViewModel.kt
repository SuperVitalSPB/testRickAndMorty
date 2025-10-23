package com.supervital.rickandmorty.feature.mainlist

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supervital.rickandmorty.models.CharacterInfo
import com.supervital.rickandmorty.usecase.CharacterGetListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val characterGetListUseCase: CharacterGetListUseCase
) : ViewModel() {

    private val _items = mutableStateListOf<CharacterInfo>()
    val items: List<CharacterInfo> = _items
    private var maxPages = 1
    private var currentPage = 1
    var isLoading = false


    suspend fun loadMoreItems() {
        if (isLoading || currentPage > maxPages) return
        isLoading = true
        val data = characterGetListUseCase(currentPage)
        maxPages = data.info.pages
        _items.addAll(data.characters)
        isLoading = false
        currentPage++
    }
}