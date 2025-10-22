package com.supervital.rickandmorty.usecase

import com.supervital.rickandmorty.repository.CharacterRepository
import com.supervital.rickandmorty.models.CharacterInfo
import javax.inject.Inject

class CharacterGetDataUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(idCharacter: Long): CharacterInfo
        = characterRepository.getCharacter(idCharacter)
}