package com.supervital.rickandmorti.data.mappers

import com.supervital.rickandmorti.data.models.CharacterEntity
import com.supervital.rickandmorti.models.CharacterInfo
import com.supervital.rickandmorti.models.Status

fun CharacterEntity.map() =
    CharacterInfo(
        id = this.id,
        name = this.name,
        species = this.species,
        status = Status.entries.find { it.statusString == this.status } ?: Status.UNKNOWN,
        gender = this.gender,
        image = this.image
)