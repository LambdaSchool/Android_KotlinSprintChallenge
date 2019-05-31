package com.example.android_kotlinsprintchallenge

import kotlinx.serialization.Serializable

@Serializable
//getting the json in total
data class VideoData(
    val collection: String,
    val credits: String,
    val image: String,
    val image_retina: String,
    val mission: String,
    val name: String,
    val short_description: String,
    //this is the list of different qualities
    val video_files: List<VideoFile>,
    val youtube_id: String
) {
    //says its not being used but it is on main activity line 33, I suspect its the emulator acting up ( working exactly how I am wanting it to)
    fun getLastVideoUrl(): String {
        return video_files.first().file_url
    }

    //hardcoded api url to pull from
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