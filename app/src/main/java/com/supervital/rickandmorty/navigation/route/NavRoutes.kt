package com.supervital.rickandmorty.navigation.route

sealed class NavRoutes(val route: String, val paramName: String) {
    object MainList : NavRoutes("main_list", EMPTY_STRING)
    object Details : NavRoutes("details", "id")

    companion object {
        const val EMPTY_STRING = ""
    }
}