package com.joshuahalvorson.android_kotlinsprintchallenge.model

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

const val VIDEOURLTOGET = 1

@Serializable
data class VideosList(val videos: List<Video>?)

@Serializable
data class Video(
    val id: Int? = -1,
    val name: String? = "",
    @Optional
    val news_name: String? = "",
    val image: String? = "",
    val collection: String? = ""
)