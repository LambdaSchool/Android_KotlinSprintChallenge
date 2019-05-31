package com.vivekvishwanath.android_kotlinsprintchallenge

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class VideoListItem(val id: Int?, val name: String?,
                         @Optional val news_name: String? = null, val image: String?,
                         val collection: String?): java.io.Serializable

@Serializable
data class Video(val name: String?, val short_description: String?,
                 @Optional val youtube_id: String? = null, val credits: String?,
                 val mission: String?, val collection: String?,
                 val image: String?, val image_retina: String?,
                 @Optional val html_5_video: Html5Video? = null,
                 @Optional val video_files: List<VideoFile>? = null): java.io.Serializable

@Serializable
data class Html5Video(val video_url: String?, val poster_url: String?): java.io.Serializable

@Serializable
data class VideoFile(val file_url: String?, val file_size: Int?,
                     val width: Int?, val height: Int?,
                     @Optional val frame_rate: String? = null, val format: String?): java.io.Serializable