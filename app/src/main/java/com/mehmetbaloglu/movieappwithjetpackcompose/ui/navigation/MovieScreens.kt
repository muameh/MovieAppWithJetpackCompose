package com.mehmetbaloglu.movieappwithjetpackcompose.ui.navigation

enum class MovieScreens(val screenName: String) {
    HomeScreen("HomeScreen"),
    DetailsScreen("DetailsScreen");

    companion object {
        fun fromRoute(route: String?): MovieScreens =
            when (route?.substringBefore("/")) {
                HomeScreen.screenName -> HomeScreen
                DetailsScreen.screenName -> DetailsScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }

    }
}
