package com.joshuahalvorson.android_kotlinsprintchallenge.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoFiles(
    val file_url: String? = "",
    val file_size: Number? = -1,
    val width: Number? = -1,
    val height: Number? = -1,
    val frame_rate: String? = "",
    val format: String? = ""
)