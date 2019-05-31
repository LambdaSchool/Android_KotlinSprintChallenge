package com.example.android_kotlinsprintchallenge

import kotlinx.serialization.Serializable

@Serializable
data class VideoData(
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
{
    fun getLastVideoUrl():String {
        return video_files.first().file_url
    }
    companion object {
        const val url = "http://hubblesite.org/api/v3/video/42"
    }

}



@Serializable
data class VideoFile(
    val file_size: Int,
    val file_url: String,
    val format: String,
    val frame_rate: String,
    val height: Int,
    val width: Int
)