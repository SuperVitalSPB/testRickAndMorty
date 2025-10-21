package com.supervital.rickandmorty.navigation.route

sealed class NavRoutes(val route: String, val paramName: String) {
    object MainList : NavRoutes("main_list", "")
    object Details : NavRoutes("details", "id")
}