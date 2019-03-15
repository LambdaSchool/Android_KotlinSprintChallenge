package com.joshuahalvorson.android_kotlinsprintchallenge.model

import kotlinx.serialization.Serializable


@Serializable
data class Video(
    val id: Number? = -1,
    val name: String? = "",
    val image: String? = "",
    val collection: String? = ""
)

@Serializable
data class VideoDetails(
    val name: String? = "",
    val short_description: String? = "",
    val youtube_id: String? = "",
    val credits: String? = "",
    val mission: String? = "",
    val collection: String? = "",
    val image: String? = "",
    val image_retina: String? = "",
    val video_files: List<VideoFiles>? = listOf<VideoFiles>()
)

fun VideoDetails.getVideoUrl(): VideoFiles? {
    return this.video_files?.get(1)
}

@Serializable
data class VideoFiles(
    val file_url: String? = "",
    val file_size: Number? = -1,
    val width: Number? = -1,
    val height: Number? = -1,
    val frame_rate: String? = "",
    val format: String? = ""
)
