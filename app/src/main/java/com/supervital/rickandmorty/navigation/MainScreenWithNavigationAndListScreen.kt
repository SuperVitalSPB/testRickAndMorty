package com.supervital.rickandmorty.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.supervital.rickandmorty.feature.details.DetailsScreen
import com.supervital.rickandmorty.feature.mainlist.MainListScreen
import com.supervital.rickandmorty.navigation.route.NavItem
import com.supervital.rickandmorty.navigation.route.NavItems
import com.supervital.rickandmorty.navigation.route.NavRoutes

@Composable
fun MainScreenWithNavigationAndListScreen(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NavItems.getNavItem( backStackEntry?.destination?.route)

    Scaffold(
        topBar = {
            TopBarApplication(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
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

                    MainListScreen()
                }
                composable(NavRoutes.Details.route) {
                    DetailsScreen()
                }
            }
        }
    }
}

@Composable
fun TopBarApplication(
    currentScreen: NavItem,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    /*TopAppBar(
        title = { Text(stringResource(currentScreen.topTitleRes)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF007AFF), // Цвет фона панели
            titleContentColor = Color.White, // Цвет текста заголовка
            navigationIconContentColor = Color.White, // Цвет иконки навигации
            actionIconContentColor = Color.White // Цвет иконок действий
        )
    )*/
}