package com.nammamistri.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nammamistri.app.ui.screens.calculator.CalculatorScreen
import com.nammamistri.app.ui.screens.home.HomeScreen
import com.nammamistri.app.ui.screens.labor.LaborScreen
import com.nammamistri.app.ui.screens.photos.PhotosScreen
import com.nammamistri.app.ui.screens.rates.RatesScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Calculator : Screen("calculator")
    object Labor : Screen("labor/{siteId}") {
        fun createRoute(siteId: Long) = "labor/$siteId"
    }
    object Photos : Screen("photos/{siteId}") {
        fun createRoute(siteId: Long) = "photos/$siteId"
    }
    object Rates : Screen("rates")
}

@Composable
fun NavGraph(
    onToggleDarkMode: () -> Unit,
    isDarkMode: Boolean
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                onToggleDarkMode = onToggleDarkMode,
                isDarkMode = isDarkMode
            )
        }
        composable(Screen.Calculator.route) {
            CalculatorScreen(navController = navController)
        }
        composable(
            route = Screen.Labor.route,
            arguments = listOf(navArgument("siteId") { type = NavType.LongType })
        ) { backStack ->
            val siteId = backStack.arguments?.getLong("siteId") ?: 0L
            LaborScreen(navController = navController, siteId = siteId)
        }
        composable(
            route = Screen.Photos.route,
            arguments = listOf(navArgument("siteId") { type = NavType.LongType })
        ) { backStack ->
            val siteId = backStack.arguments?.getLong("siteId") ?: 0L
            PhotosScreen(navController = navController, siteId = siteId)
        }
        composable(Screen.Rates.route) {
            RatesScreen(navController = navController)
        }
    }
}