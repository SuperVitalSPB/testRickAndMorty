package com.supervital.rickandmorty.feature.mainlist

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.supervital.rickandmorti.models.CharacterInfo
import com.supervital.rickandmorti.usecase.CharacterGetListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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