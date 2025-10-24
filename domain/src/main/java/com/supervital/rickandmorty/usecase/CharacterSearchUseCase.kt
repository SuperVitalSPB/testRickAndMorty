package com.supervital.rickandmorty.usecase

import com.supervital.rickandmorty.models.CharactersListInfo
import com.supervital.rickandmorty.repository.CharacterRepository
import javax.inject.Inject

class CharacterSearchUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(params: Map<String, String>): CharactersListInfo
        = characterRepository.searchCharacters(params)
}