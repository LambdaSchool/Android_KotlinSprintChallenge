package com.jakeesveld.sprint11

import kotlinx.serialization.Serializable

@Serializable
data class VideoListItem(
    val id: Int?,
    val name: String?,
    val news_name: String?,
    val image: String?,
    val collection: String?
)

@Serializable
data class VideoItem(
    val name: String?,
    val short_description: String?,
    val youtube_id: String?,
    val credits: String?,
    val mission: String?,
    val collection: String?,
    val image: String?,
    val image_retina: String?,
    val html_5_video: VideoFile?,
    val video_files: List<VideoDetails>?
)

@Serializable
data class VideoFile(val video_url: String?, val poster_url: String?)

@Serializable
data class VideoDetails(
    val file_url: String?,
    val file_size: Int?,
    val width: Int?,
    val height: Int?,
    val frame_rate: String?,
    val format: String?
)