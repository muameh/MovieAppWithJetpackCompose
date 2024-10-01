package com.mehmetbaloglu.movieappwithjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mehmetbaloglu.movieappwithjetpackcompose.screens.DetailsScreen
import com.mehmetbaloglu.movieappwithjetpackcompose.screens.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.screenName) {
        composable(MovieScreens.HomeScreen.screenName) {
            HomeScreen(navController = navController)
        }
        composable(
            MovieScreens.DetailsScreen.screenName+"/{movie}",
            arguments = listOf(navArgument("movie") { type = NavType.StringType })){
            DetailsScreen(navController = navController, it.arguments?.getString("movie"))
        }


    }
}