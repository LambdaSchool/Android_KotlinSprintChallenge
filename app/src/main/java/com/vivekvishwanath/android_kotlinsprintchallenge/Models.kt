package com.vivekvishwanath.android_kotlinsprintchallenge

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class VideoListItem(val id: Int?, val name: String?,
                         val news_name: String? = null, val image: String?,
                         val collection: String?)

@Serializable
data class Video(val name: String?, val short_description: String?,
                 val youtube_id: String? = null, val credits: String?,
                 val mission: String?, val collection: String?,
                 val image: String?, val image_retina: String?,
                 val html_5_video: Html5Video? = null,
                 val video_files: List<VideoFile>? = null)

@Serializable
data class Html5Video(val video_url: String?, val poster_url: String?)

@Serializable
data class VideoFile(val file_url: String?, val file_size: Int?,
                     val width: Int?, val height: Int?,
                     val frame_rate: String?, val format: String?)