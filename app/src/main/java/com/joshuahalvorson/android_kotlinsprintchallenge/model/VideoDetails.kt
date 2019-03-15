package com.joshuahalvorson.android_kotlinsprintchallenge.model

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

const val VIDEOTOGET = 3

@Serializable
data class VideoDetails(
    val name: String? = "",
    val short_description: String? = "",
    val credits: String? = "",
    @Optional
    val news_name: String? = "",
    @Optional
    val youtube_id: String? = "",
    val mission: String? = "",
    val collection: String? = "",
    val image: String? = "",
    val image_retina: String? = "",
    @Optional
    val html_5_video: Html_5_video? = Html_5_video(),
    val video_files: List<VideoFiles>? = listOf(VideoFiles())
)

@Serializable
data class Html_5_video(
    val video_url: String? = "",
    val poster_url: String? = ""
)

fun VideoDetails.getVideoUrl(): VideoFiles? {
    return this.video_files?.get(VIDEOTOGET)
}
