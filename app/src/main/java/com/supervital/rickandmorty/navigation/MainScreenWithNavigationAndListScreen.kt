package com.supervital.rickandmorty.navigation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.supervital.rickandmorty.feature.details.DetailsScreen
import com.supervital.rickandmorty.feature.details.DetailsTopAppBarScreen
import com.supervital.rickandmorty.feature.mainlist.MainListScreen
import com.supervital.rickandmorty.feature.mainlist.MainListViewModel
import com.supervital.rickandmorty.feature.mainlist.MainTopAppBarScreen
import com.supervital.rickandmorty.navigation.route.NavItems
import com.supervital.rickandmorty.navigation.route.NavRoutes
import com.supervital.rickandmorty.navigation.route.PARAM_CHARACTER_ID

const val TAG = "charTest:MainScreenWithNavigationAndListScreen"

@Composable
fun MainScreenWithNavigationAndListScreen(
    viewModel: MainListViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NavItems.getNavItem(backStackEntry?.destination?.route)

    Scaffold(
        topBar = {
            TopBarApplication(
                viewModel = viewModel,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                currentRoute = currentScreen.route
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
                    MainListScreen(
                        viewModel = viewModel,
                        showDetails = { id ->
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
    viewModel: MainListViewModel,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    currentRoute: String
) {
    when (currentRoute) {
        NavRoutes.Details.route -> DetailsTopAppBarScreen(
            canNavigateBack,
            navigateUp,
            modifier
        )
        NavRoutes.MainList.route -> MainTopAppBarScreen(viewModel)
        else -> {}
    }
}