package com.assignment.navigation.constants

object NavigationConstants {

    internal const val FEATURE_PATH = "com.assignment"

    // home
     const val HOME_PATH = "homeScreenRoute"
     const val FAVORITE_PATH = "favoriteScreenRoute"


    fun replaceParams(path:String, key:String, value:String) =
        path.replace(key,value)


}