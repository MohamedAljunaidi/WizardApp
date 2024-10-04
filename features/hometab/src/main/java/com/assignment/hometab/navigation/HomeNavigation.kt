package com.assignment.hometab.navigation

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.assignment.hometab.presentation.favorite.FavoriteRoute
import com.assignment.hometab.presentation.home.HomeScreen
import com.assignment.hometab.presentation.wizarddetails.WizardDetailsScreen
import com.assignment.navigation.constants.NavigationConstants
import com.assignment.navigation.constants.NavigationConstants.replaceParams

fun NavGraphBuilder.homeNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    homeRoute(
        modifier = modifier,
        onWizardItemClick = navController::navigateToWizardDetailsScreen,
    )

    favoriteRoute(
        onBackBtnClick = { backDispatcher ->
            backDispatcher?.onBackPressed()
        }
    )
    wizardDetailsRoute(
        modifier = modifier,
        onBackBtnClick = { backDispatcher ->
            backDispatcher?.onBackPressed()
        }
    )

}

fun NavGraphBuilder.homeRoute(
    modifier: Modifier = Modifier,
    onWizardItemClick: (String) -> Unit,
) {
    composable(route = NavigationConstants.HOME_PATH) {
        HomeScreen(
            modifier = modifier,
            onWizardItemClick = onWizardItemClick
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

fun NavGraphBuilder.wizardDetailsRoute(
    modifier: Modifier = Modifier,
    onBackBtnClick: (OnBackPressedDispatcher?) -> Unit,
) {
    composable(
        route = NavigationConstants.WIZARD_DETAILS_PATH,
        arguments = listOf(
            navArgument(
                name = NavigationConstants.WIZARD_ID_PARAMS
            ) {
                type = NavType.StringType
                defaultValue = ""
            },
        )
    ) {
        val wizardID = it.arguments?.getString(NavigationConstants.WIZARD_ID_PARAMS)


        WizardDetailsScreen(
            modifier = modifier,
            onBackBtnClick = onBackBtnClick, wizardId = wizardID
        )
    }
}


fun NavController.navigateToWizardDetailsScreen(wizardId: String) {

    navigate(
        replaceParams(
            NavigationConstants.WIZARD_DETAILS_PATH,
            "{${NavigationConstants.WIZARD_ID_PARAMS}}",
            wizardId
        )
    )
}
