package com.supervital.rickandmorty.models

data class LocationInfo(
    val id: Int = 0,
    val name: String = EMPTY_STRING,
    val type: String = EMPTY_STRING,
    val dimension: String = EMPTY_STRING,
    val url: String = EMPTY_STRING
) {
    companion object {
        const val EMPTY_STRING = ""
    }
}