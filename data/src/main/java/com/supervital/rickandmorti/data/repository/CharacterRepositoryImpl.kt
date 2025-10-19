package com.supervital.rickandmorti.data.repository

import com.supervital.rickandmorti.repository.CharacterRepository
import com.supervital.rickandmorti.data.api.CharacterService
import com.supervital.rickandmorti.data.mappers.map
import com.supervital.rickandmorti.models.CharacterInfo
import com.supervital.rickandmorti.models.CharactersListInfo
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    val characterService: CharacterService
) : CharacterRepository {

    override suspend fun getCharacters(page: Int): CharactersListInfo =
        characterService.getCharacters(page).map()

    override suspend fun getCharacter(id: Long): CharacterInfo =
        characterService.getCharacter(id = id).map()

}