package com.supervital.rickandmorty.data.api

import com.supervital.rickandmorty.data.models.CharacterEntity
import com.supervital.rickandmorty.data.models.CharactersListEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharactersListEntity

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Long): CharacterEntity
}
