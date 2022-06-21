package com.example.homework3.data.model

import com.google.gson.annotations.SerializedName

internal data class Name(
    @SerializedName("common")
    val name: String?
)