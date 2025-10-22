package com.supervital.rickandmorty.feature.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supervital.rickandmorty.models.CharacterInfo
import com.supervital.rickandmorty.usecase.CharacterGetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val characterGetDataUseCase: CharacterGetDataUseCase
) : ViewModel() {

    private val _data = MutableLiveData<CharacterInfo>()
    val data: LiveData<CharacterInfo>
        get() = _data

    fun loadData(idCharacter: Long) {
        viewModelScope.launch {
            _data.value = characterGetDataUseCase(idCharacter)
        }
    }
}