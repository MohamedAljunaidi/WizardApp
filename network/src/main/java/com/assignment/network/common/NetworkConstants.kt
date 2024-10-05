package com.assignment.network.common

import com.assignment.core.BuildConfig

object NetworkConstants {

    const val TIMEOUT_IN_SECONDS = 30L
    const val BASE_URL = BuildConfig.BASE_URL
    object ErrorCodes {

        const val CLIENT_ERROR_FORBIDDEN = 403
        const val CLIENT_ERROR_RANGE_START = 400
        const val CLIENT_ERROR_RANGE_END = 499
        const val SERVER_ERROR_RANGE_START = 500
        const val SERVER_ERROR_RANGE_END = 599

    }

}