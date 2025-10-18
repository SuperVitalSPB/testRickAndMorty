package com.supervital.rickandmorti.usecase

import com.supervital.rickandmorti.repository.CharacterRepository
import com.supervital.rickandmorti.models.CharacterInfo
import javax.inject.Inject

class CharacterGetDataUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(idCharacter: Long): CharacterInfo
        = characterRepository.getCharacter(idCharacter)
}