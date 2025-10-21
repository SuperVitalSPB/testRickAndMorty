package com.supervital.rickandmorti.models

data class CharactersListInfo(
    val info: CharacterMetaInfo = CharacterMetaInfo(),
    val characters: List<CharacterInfo> = emptyList<CharacterInfo>()
)