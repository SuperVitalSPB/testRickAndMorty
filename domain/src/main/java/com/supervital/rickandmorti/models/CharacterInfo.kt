package com.supervital.rickandmorti.models

data class CharacterInfo(
    val id: Int,
    val name: String,
    val species: String,
    val status: Status,
    val gender: String,
    val image: String
)
