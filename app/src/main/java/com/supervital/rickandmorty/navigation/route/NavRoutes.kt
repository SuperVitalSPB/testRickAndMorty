package com.supervital.rickandmorty.navigation.route

const val PARAM_CHARACTER_ID = "characterId"

sealed class NavRoutes(val route: String) {

    object MainList : NavRoutes("main_list")

    object Details : NavRoutes("details/{$PARAM_CHARACTER_ID}") {
        fun paramRoute(id: Long) =
            route.replace("{$PARAM_CHARACTER_ID}", id.toString())
    }
}