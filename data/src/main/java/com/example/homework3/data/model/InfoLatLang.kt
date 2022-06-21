package com.example.homework3.data.model

import com.google.gson.annotations.SerializedName

internal data class InfoLatLng(
    @SerializedName("latlng")
    val latLng: List<Double> = listOf(53.8980, 27.5827)
)