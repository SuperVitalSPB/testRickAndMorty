package com.supervital.rickandmorti.data.api

import com.supervital.rickandmorti.data.models.CharacterEntity
import com.supervital.rickandmorti.data.models.CharacterResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(): CharacterResponseModel

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Long): CharacterEntity
}
