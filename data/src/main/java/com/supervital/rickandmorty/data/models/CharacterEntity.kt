package com.supervital.rickandmorty.data.models

import com.supervital.rickandmorty.models.LocationInfo

data class CharacterEntity(
    val id: Long,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val image: String,
    val type: String,
    val location: LocationInfo
)
