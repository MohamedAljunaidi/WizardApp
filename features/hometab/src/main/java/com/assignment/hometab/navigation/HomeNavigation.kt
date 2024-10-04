package com.assignment.hometab.navigation

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.assignment.hometab.presentation.favorite.FavoriteRoute
import com.assignment.hometab.presentation.home.HomeScreen
import com.assignment.navigation.constants.NavigationConstants

fun NavGraphBuilder.homeNavigation(
    navController: NavHostController,
    modifier : Modifier = Modifier
) {
    homeRoute(
        modifier = modifier,
    )

    favoriteRoute(
        onBackBtnClick = { backDispatcher ->
            backDispatcher?.onBackPressed()
        }
    )

}

fun NavGraphBuilder.homeRoute(
    modifier: Modifier = Modifier,
) {
    composable(route = NavigationConstants.HOME_PATH) {
        HomeScreen(
            modifier = modifier,
        )
    }
}


fun NavGraphBuilder.favoriteRoute(
    onBackBtnClick: (OnBackPressedDispatcher?) -> Unit,
) {
    composable(route = NavigationConstants.FAVORITE_PATH) {
        FavoriteRoute(
            onBackBtnClick = onBackBtnClick
        )
    }
}

