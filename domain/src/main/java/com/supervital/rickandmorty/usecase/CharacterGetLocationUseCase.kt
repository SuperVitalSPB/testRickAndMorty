package com.supervital.rickandmorty.usecase

import com.supervital.rickandmorty.models.LocationInfo
import com.supervital.rickandmorty.repository.CharacterRepository
import javax.inject.Inject

class CharacterGetLocationUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(fullUrl: String): LocationInfo
        = characterRepository.getLocation(fullUrl)
}