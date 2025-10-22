package com.supervital.rickandmorty.usecase

import com.supervital.rickandmorty.models.CharactersListInfo
import com.supervital.rickandmorty.repository.CharacterRepository
import javax.inject.Inject

class CharacterGetListUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(page: Int): CharactersListInfo
        = characterRepository.getCharacters(page)
}