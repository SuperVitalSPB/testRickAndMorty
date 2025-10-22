package com.supervital.rickandmorty.data.api

import com.supervital.rickandmorty.data.models.CharacterEntity
import com.supervital.rickandmorty.data.models.CharactersListEntity
import com.supervital.rickandmorty.data.models.LocationEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharactersListEntity

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Long): CharacterEntity

    @GET
    suspend fun getLocation(@Url fullUrl: String): LocationEntity
}
