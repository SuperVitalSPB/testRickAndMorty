package com.supervital.rickandmorty.feature.mainlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supervital.rickandmorti.models.CharactersListInfo
import com.supervital.rickandmorti.usecase.CharacterGetListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val characterGetListUseCase: CharacterGetListUseCase
) : ViewModel() {

    private val _data = MutableLiveData<CharactersListInfo>()
    val data: LiveData<CharactersListInfo>
        get() = _data

    fun loadData(page: Int) {
        viewModelScope.launch {
            _data.value = characterGetListUseCase(page)
        }
    }
}