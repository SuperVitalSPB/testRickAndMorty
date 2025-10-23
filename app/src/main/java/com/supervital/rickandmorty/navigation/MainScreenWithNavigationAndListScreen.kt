package com.supervital.rickandmorty.navigation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.supervital.rickandmorty.feature.details.DetailsScreen
import com.supervital.rickandmorty.feature.details.TitleDetailScreen
import com.supervital.rickandmorty.feature.mainlist.MainListScreen
import com.supervital.rickandmorty.feature.mainlist.SearchBarCharacterScreen
import com.supervital.rickandmorty.feature.mainlist.TAG
import com.supervital.rickandmorty.navigation.route.NavItems
import com.supervital.rickandmorty.navigation.route.NavRoutes
import com.supervital.rickandmorty.navigation.route.PARAM_CHARACTER_ID

@Composable
fun MainScreenWithNavigationAndListScreen(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NavItems.getNavItem(backStackEntry?.destination?.route)

    Scaffold(
        topBar = {
            TopBarApplication(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                titleScreen = {
                    when (currentScreen.route) {
                        NavRoutes.Details.route -> TitleDetailScreen()
                        NavRoutes.MainList.route -> SearchBarCharacterScreen()
                        else -> {}
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoutes.MainList.route,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                composable(NavRoutes.MainList.route) {
                    MainListScreen(showDetails = { id ->
                        Log.d(TAG, "id = $id")
                        navController.navigate(NavRoutes.Details.paramRoute(id))
                    })
                }
                composable(
                    route = NavRoutes.Details.route,
                    arguments = listOf(navArgument(PARAM_CHARACTER_ID) { type = NavType.LongType })
                ) { backStackEntry ->
                        backStackEntry.arguments?.getLong(PARAM_CHARACTER_ID)?.let {
                            DetailsScreen(idCharacter = it)
                        }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarApplication(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    titleScreen: @Composable () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = titleScreen,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "back_button"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black, // Цвет фона панели
            titleContentColor = Color.White, // Цвет текста заголовка
            navigationIconContentColor = Color.White, // Цвет иконки навигации
            actionIconContentColor = Color.White // Цвет иконок действий
        )
    )
}