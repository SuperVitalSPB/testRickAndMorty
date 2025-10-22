package com.supervital.rickandmorty.data.mappers

import com.supervital.rickandmorty.data.models.CharacterEntity
import com.supervital.rickandmorty.data.models.CharacterMetaEntity
import com.supervital.rickandmorty.data.models.CharactersListEntity
import com.supervital.rickandmorty.models.CharacterInfo
import com.supervital.rickandmorty.models.CharacterMetaInfo
import com.supervital.rickandmorty.models.CharactersListInfo
import com.supervital.rickandmorty.models.Status

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
        characters = this.results.map {
            it.map()
        }
    )
