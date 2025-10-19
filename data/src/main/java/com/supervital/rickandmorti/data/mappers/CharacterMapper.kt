package com.supervital.rickandmorti.data.mappers

import com.supervital.rickandmorti.data.models.CharacterEntity
import com.supervital.rickandmorti.data.models.CharacterMetaEntity
import com.supervital.rickandmorti.data.models.CharactersListEntity
import com.supervital.rickandmorti.models.CharacterInfo
import com.supervital.rickandmorti.models.CharacterMetaInfo
import com.supervital.rickandmorti.models.CharactersListInfo
import com.supervital.rickandmorti.models.Status

fun CharacterMetaEntity.map() =
    CharacterMetaInfo(
        count = this.count,
        pages = this.pages,
        next = this.next,
        prev = this.prev
    )

fun CharacterEntity.map() =
    CharacterInfo(
        id = this.id,
        name = this.name,
        species = this.species,
        status = Status.entries.find { it.statusString == this.status } ?: Status.UNKNOWN,
        gender = this.gender,
        image = this.image
    )

fun CharactersListEntity.map() =
    CharactersListInfo (
        info = this.info.map(),
        list = this.results.map {
            it.map()
        }
    )
