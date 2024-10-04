package com.assignment.direction.home

import android.os.Parcelable
import com.assignment.navigation.bases.IBaseDestination
import com.assignment.navigation.constants.NavigationConstants
import kotlinx.parcelize.Parcelize

@Parcelize
enum class HomeDestinationEnum : Parcelable, IBaseDestination {

    Home {

        override fun getDestination(): String {
            return NavigationConstants.HOME_PATH
        }
    },



}

