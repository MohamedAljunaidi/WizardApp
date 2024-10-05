package com.assignment.hometab.navigation

import android.content.Context
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.assignment.direction.elixirs.ElixirNavigator
import com.assignment.direction.elixirs.ElixirsDestinationEnum
import com.assignment.extension.navigateToDirection
import com.assignment.hometab.presentation.favorite.FavoriteScreen
import com.assignment.hometab.presentation.wizard.HomeScreen
import com.assignment.hometab.presentation.wizarddetails.WizardDetailsScreen
import com.assignment.navigation.constants.NavigationConstants
import com.assignment.navigation.constants.NavigationConstants.replaceParams

fun NavGraphBuilder.wizardNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    wizardRoute(
        modifier = modifier,
        onWizardItemClick = navController::navigateToWizardDetailsScreen,
    )

    favoriteRoute(
        modifier = modifier,
        onWizardItemClick = navController::navigateToWizardDetailsScreen,
    )
    wizardDetailsRoute(
        modifier = modifier,
        onBackBtnClick = { backDispatcher ->
            backDispatcher?.onBackPressed()
        },
        onElixirItemClick = { context, elixirsId ->
            ElixirNavigator.navigateToDirection(
                context,
                destination = ElixirsDestinationEnum.ELIXIRS_DETAILS,
                data = bundleOf(NavigationConstants.ELIXIR_ID_PARAMS to elixirsId),
            )
        }
    )

}

fun NavGraphBuilder.wizardRoute(
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
    modifier: Modifier = Modifier,
    onWizardItemClick: (String) -> Unit,
) {
    composable(route = NavigationConstants.FAVORITE_PATH) {
        FavoriteScreen(
            modifier = modifier,
            onFavoriteItemClick = onWizardItemClick

        )
    }
}

fun NavGraphBuilder.wizardDetailsRoute(
    modifier: Modifier = Modifier,
    onBackBtnClick: (OnBackPressedDispatcher?) -> Unit,
    onElixirItemClick: (Context, String) -> Unit,
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
            onBackBtnClick = onBackBtnClick, wizardId = wizardID,
            onElixirItemClick = onElixirItemClick
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
