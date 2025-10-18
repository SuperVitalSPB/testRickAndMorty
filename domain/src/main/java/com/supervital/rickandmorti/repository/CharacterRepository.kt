package com.supervital.rickandmorti.repository

import com.supervital.rickandmorti.models.CharacterInfo

interface CharacterRepository {

    suspend fun getCharacters(): List<CharacterInfo>

    suspend fun getCharacter(id: Long): CharacterInfo
}