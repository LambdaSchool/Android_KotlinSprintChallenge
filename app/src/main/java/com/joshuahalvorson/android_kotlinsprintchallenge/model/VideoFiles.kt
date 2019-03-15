package com.joshuahalvorson.android_kotlinsprintchallenge.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoFiles(
    val file_url: String? = "",
    val file_size: Int? = -1,
    val width: Int? = -1,
    val height: Int? = -1,
    val frame_rate: String? = "",
    val format: String? = ""
)