package com.assignment.elixirs.navigation


import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.assignment.elixirs.presentation.ElixirsDetailsScreen
import com.assignment.navigation.constants.NavigationConstants

fun NavGraphBuilder.elixirsNavigation(
    navController: NavHostController,
) {

    elixirDetailsScreen(
        onBackBtnClick = { backDispatcher ->
            backDispatcher?.onBackPressed()
        }
    )
}

fun NavGraphBuilder.elixirDetailsScreen(
    onBackBtnClick: (OnBackPressedDispatcher?) -> Unit,
) {
    composable(route = NavigationConstants.ELIXIRS_DETAILS_PATH) {
        ElixirsDetailsScreen(
            onBackBtnClick = onBackBtnClick,)
    }
}
