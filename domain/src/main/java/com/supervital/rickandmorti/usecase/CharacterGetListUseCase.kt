package com.supervital.rickandmorti.usecase

import com.supervital.rickandmorti.models.CharactersListInfo
import com.supervital.rickandmorti.repository.CharacterRepository
import javax.inject.Inject

class CharacterGetListUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(page: Int): CharactersListInfo
        = characterRepository.getCharacters(page)
}