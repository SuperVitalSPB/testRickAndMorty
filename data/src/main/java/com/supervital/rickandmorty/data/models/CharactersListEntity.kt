package com.supervital.rickandmorty.data.models

data class CharactersListEntity(
    val info: CharacterMetaEntity,
    val results: List<CharacterEntity>
)
