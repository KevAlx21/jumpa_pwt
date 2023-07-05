package com.mytour.jumpapwt.data.item

import java.io.Serializable

data class TourismItem(
    val tourismName: String = "",
    val tourismOpenHour: String = "",
    val tourismRating: String = "",
    val tourismDescription: String = "",
    val tourismCall: String = "",
    val tourismAddress: String = "",
    val tourismLon: Double = 0.0,
    val tourismLat: Double = 0.0,
    val tourismPhoto: String? = null
) : Serializable
