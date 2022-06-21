package com.example.homework3.data.model

import com.google.gson.annotations.SerializedName

internal data class CountryDTO (
    val name: Name,
    @SerializedName("capitalInfo")
    val infoLatLng: InfoLatLng,
        )