package com.lambdaschool.android_kotlinsprintchallenge

import android.support.annotation.UiThread
import kotlinx.serialization.Serializable

@Serializable
data class VideoModel(
        val name: String? = "",
        val short_description: String? = "",
        val credits: String? = "",
        val news_name: String? = "",
        val mission: String? = "",
        val collection: String? = "",
        val image: String? = "",
        val image_retina: String? = "",
        val html_5_video: Html_5_video? = Html_5_video(),
        val video_files: List<VideoList>? = listOf()
)

@Serializable
data class Html_5_video(
        val video_url: String? = "",
        val poster_url: String? = ""
)

@Serializable
data class VideoList(
        val file_url: String? = "",
        val file_size: Int? = -1,
        val width: Int? = -1,
        val height: Int? = -1,
        val frame_rate: String? = "",
        val format: String? = ""
) {
    companion object {
        const val DESIRED_VIDEO_QUALITY: Int = 1
    }
}

@UiThread
fun VideoModel.getVideoUrl(): String {
    return if (this.video_files?.size!! > 1)
        this.video_files.get(VideoList.DESIRED_VIDEO_QUALITY).file_url ?: ""
    else
        this.video_files.get(0).file_url ?: ""
}