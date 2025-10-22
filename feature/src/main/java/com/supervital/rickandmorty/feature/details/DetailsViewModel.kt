package com.supervital.rickandmorty.feature.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supervital.rickandmorty.models.CharacterInfo
import com.supervital.rickandmorty.models.LocationInfo
import com.supervital.rickandmorty.usecase.CharacterGetDataUseCase
import com.supervital.rickandmorty.usecase.CharacterGetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val characterGetDataUseCase: CharacterGetDataUseCase,
    private val characterGetLocationUseCase: CharacterGetLocationUseCase
) : ViewModel() {

    private val _data = MutableLiveData<CharacterInfo>(CharacterInfo())
    val data: LiveData<CharacterInfo> = _data

    private val _dataLocation = MutableLiveData<LocationInfo>(LocationInfo())
    val dataLocation: LiveData<LocationInfo> = _dataLocation


    fun loadData(idCharacter: Long) {
        viewModelScope.launch {
            _data.value = characterGetDataUseCase(idCharacter)
            val urlLocation = _data.value?.location?.url ?: ""
            if (urlLocation.isNotEmpty()) {
                _dataLocation.value = characterGetLocationUseCase(urlLocation)
            }
        }
    }
}