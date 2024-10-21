package com.xsoft.satori.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.xsoftcdmx.designsystem.transitions.screenFadeIn
import com.xsoftcdmx.designsystem.transitions.screenFadeOut
import com.xsoftcdmx.designsystem.transitions.screenSlideIn
import com.xsoftcdmx.designsystem.transitions.screenSlideOut
import com.xsoftcdmx.list_detail.navigation.POKEMON_GRAPH_ROUTE
import com.xsoftcdmx.list_detail.navigation.pokemonDetailRoute
import com.xsoftcdmx.list_detail.navigation.pokemonScreen

@Composable
fun RootHost() {
    val rootController = rememberNavController()
    NavHost(
        navController = rootController,
        startDestination = POKEMON_GRAPH_ROUTE,
        enterTransition = { screenSlideIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenSlideOut() }
    ) {
        pokemonScreen(
            goToDetail = {
                rootController.navigate(pokemonDetailRoute(it))
            }
        )
    }

}