package com.supervital.rickandmorty.models

data class CharacterInfo(
    val id: Long = 0L,
    val name: String = EMPTY_STRING,
    val species: String = EMPTY_STRING,
    val statusInfo: StatusInfo = StatusInfo.UNKNOWN,
    val gender: String = EMPTY_STRING,
    val image: String = EMPTY_STRING,
    val type: String = EMPTY_STRING,
    val location: LocationInfo = LocationInfo()
) {
    companion object {
        const val EMPTY_STRING = ""
    }
}
