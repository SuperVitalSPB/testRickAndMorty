package com.supervital.rickandmorti.data.models

data class CharactersListEntity(
    val info: CharacterMetaEntity,
    val results: List<CharacterEntity>
)
