package com.assignment.direction.elixirs

import android.os.Parcelable
import com.assignment.navigation.bases.IBaseDestination
import com.assignment.navigation.constants.NavigationConstants
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ElixirsDestinationEnum : Parcelable, IBaseDestination {

    ELIXIRS_DETAILS {

        override fun getDestination(): String {
            return NavigationConstants.ELIXIRS_DETAILS_PATH
        }
    },



}

