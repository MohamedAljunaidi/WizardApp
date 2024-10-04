package com.assignment.navigation.bases

import android.content.Context
import android.os.Bundle

abstract class INavigatorDirection {

    internal abstract val className: String
    internal abstract fun navigateTo(context: Context, data: Bundle? = null, destination: IBaseDestination?=null)
}