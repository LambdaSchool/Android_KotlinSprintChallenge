package com.example.android_kotlinsprintchallenge.model

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val collection: String,
    val credits: String,
    val image: String,
    val image_retina: String,
    val mission: String,
    val name: String,
    val short_description: String,
    val video_files: List<VideoFile>,
    val youtube_id: String
)


@Serializable
data class videosList(val videos: List<Video>?)



@Serializable
data class VideoFile(
    val file_size: Int,
    val file_url: String,
    val width: Int? = -1,
    val height: Int? = -1,
    @Optional
    val frame_rate: String? = "",
    val format: String? = ""
)