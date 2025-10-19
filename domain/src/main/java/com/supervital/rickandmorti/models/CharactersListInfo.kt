package com.supervital.rickandmorti.models

data class CharactersListInfo(
    val info: CharacterMetaInfo = CharacterMetaInfo(),
    val list: List<CharacterInfo> = emptyList<CharacterInfo>()
)