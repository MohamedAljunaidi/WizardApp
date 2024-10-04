package com.assignment.navigation.constants

object NavigationConstants {

    internal const val FEATURE_PATH = "com.assignment"
    const val WIZARD_ID_PARAMS = "wizardID"

    // home
    const val HOME_PATH = "homeScreenRoute"
    const val FAVORITE_PATH = "favoriteScreenRoute"
    const val WIZARD_DETAILS_PATH = "wizardDetailsScreenRoute?wizardId={${WIZARD_ID_PARAMS}}"


    fun replaceParams(path: String, key: String, value: String) =
        path.replace(key, value)


}