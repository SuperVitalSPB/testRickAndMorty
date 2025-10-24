package com.supervital.rickandmorty.repository

import com.supervital.rickandmorty.models.CharacterInfo
import com.supervital.rickandmorty.models.CharactersListInfo
import com.supervital.rickandmorty.models.LocationInfo

interface CharacterRepository {

    suspend fun getCharacters(page: Int):CharactersListInfo

    suspend fun searchCharacters(params: Map<String, String>): CharactersListInfo

    suspend fun getCharacter(id: Long): CharacterInfo

    suspend fun getLocation(fullUrl: String): LocationInfo
}