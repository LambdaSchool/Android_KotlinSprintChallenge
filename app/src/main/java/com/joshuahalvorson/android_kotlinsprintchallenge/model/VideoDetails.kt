package com.joshuahalvorson.android_kotlinsprintchallenge.model

import kotlinx.serialization.Serializable

object VideoDetails{

    const val VIDEOTOGET = 1

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
        return this.video_files?.get(VIDEOTOGET)
    }
}