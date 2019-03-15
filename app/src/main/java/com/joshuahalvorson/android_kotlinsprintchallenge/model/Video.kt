package com.joshuahalvorson.android_kotlinsprintchallenge.model

import kotlinx.serialization.Serializable

object Video{

    const val VIDEOURLTOGET = 1

    @Serializable
    data class Video(
        val id: Number? = -1,
        val name: String? = "",
        val image: String? = "",
        val collection: String? = ""
    )


}