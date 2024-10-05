package com.assignment.direction.elixirs

import android.content.Context
import android.os.Bundle
import com.assignment.extension.startIntent
import com.assignment.navigation.bases.IBaseDestination
import com.assignment.navigation.bases.INavigatorDirection
import com.assignment.navigation.constants.NavigationConstants

object ElixirNavigator : INavigatorDirection() {

    private const val ELIXIRS_ACTIVITY_PACKAGE_NAME = "elixirs"
    private const val ELIXIRS_SCREEN_NAME = "ElixirsActivity"
    private const val FEATURE_ELIXIR_PATH =
        "${NavigationConstants.FEATURE_PATH}.$ELIXIRS_ACTIVITY_PACKAGE_NAME"

    private const val ELIXIR_PACKAGE_NAME =
        "$FEATURE_ELIXIR_PATH.$ELIXIRS_SCREEN_NAME"

    override var className: String = ELIXIR_PACKAGE_NAME

    override fun navigateTo(context: Context, data: Bundle?, destination: IBaseDestination?) {
        context.apply {
            startIntent(className, data, destination)
        }
    }
}
