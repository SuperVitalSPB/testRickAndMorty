package com.supervital.rickandmorti.repository

import com.supervital.rickandmorti.models.CharacterInfo
import com.supervital.rickandmorti.models.CharactersListInfo

interface CharacterRepository {

    suspend fun getCharacters(page: Int):CharactersListInfo

    suspend fun getCharacter(id: Long): CharacterInfo
}