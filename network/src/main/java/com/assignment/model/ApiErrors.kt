package com.assignment.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class ApiErrors(
    @SerializedName("status") val status: String? = null,
    @SerializedName("title") val message: String? = null,
)
