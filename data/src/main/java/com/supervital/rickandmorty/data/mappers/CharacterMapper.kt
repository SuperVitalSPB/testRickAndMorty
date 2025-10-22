package com.supervital.rickandmorty.data.mappers

import com.supervital.rickandmorty.data.models.CharacterEntity
import com.supervital.rickandmorty.data.models.CharacterMetaEntity
import com.supervital.rickandmorty.data.models.CharactersListEntity
import com.supervital.rickandmorty.data.models.LocationEntity
import com.supervital.rickandmorty.models.CharacterInfo
import com.supervital.rickandmorty.models.CharacterMetaInfo
import com.supervital.rickandmorty.models.CharactersListInfo
import com.supervital.rickandmorty.models.LocationInfo
import com.supervital.rickandmorty.models.StatusInfo

fun LocationEntity.map() =
    LocationInfo(
        id = this.id,
        name = this.name,
        type = this.type,
        dimension = this.dimension,
        url = this.url
    )

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
        statusInfo = StatusInfo.entries.find { it.statusString == this.status } ?: StatusInfo.UNKNOWN,
        gender = this.gender,
        image = this.image,
        type = this.type,
        location = this.location
    )

fun CharactersListEntity.map() =
    CharactersListInfo (
        info = this.info.map(),
        characters = this.results.map {
            it.map()
        }
    )
