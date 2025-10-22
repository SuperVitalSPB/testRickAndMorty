package com.supervital.rickandmorty.data.repository

import com.supervital.rickandmorty.repository.CharacterRepository
import com.supervital.rickandmorty.data.api.CharacterService
import com.supervital.rickandmorty.data.mappers.map
import com.supervital.rickandmorty.models.CharacterInfo
import com.supervital.rickandmorty.models.CharactersListInfo
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    val characterService: CharacterService
) : CharacterRepository {

    override suspend fun getCharacters(page: Int): CharactersListInfo =
        characterService.getCharacters(page).map()

    override suspend fun getCharacter(id: Long): CharacterInfo =
        characterService.getCharacter(id = id).map()

}