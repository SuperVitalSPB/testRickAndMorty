package com.supervital.rickandmorty.repository

import com.supervital.rickandmorty.models.CharacterInfo
import com.supervital.rickandmorty.models.CharactersListInfo

interface CharacterRepository {

    suspend fun getCharacters(page: Int):CharactersListInfo

    suspend fun getCharacter(id: Long): CharacterInfo
}