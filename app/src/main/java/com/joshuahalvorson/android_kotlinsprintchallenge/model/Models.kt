package com.joshuahalvorson.android_kotlinsprintchallenge.model

import kotlinx.serialization.Serializable


@Serializable
data class Video(
    val id: Number?,
    val name: String?,
    val image: String?,
    val collection: String?
)

@Serializable
data class VideoDetails(
    val name: String?,
    val short_description: String?,
    val youtube_id: String?,
    val credits: String?,
    val mission: String?,
    val collection: String?,
    val image: String?,
    val image_retina: String?,
    val video_files: List<VideoFiles>?
)

@Serializable
data class VideoFiles(
    val file_url: String?,
    val file_size: Number?,
    val width: Number?,
    val height: Number?,
    val frame_rate: String?,
    val format: String?
)
