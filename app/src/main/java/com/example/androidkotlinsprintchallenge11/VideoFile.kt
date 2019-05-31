package com.example.androidkotlinsprintchallenge11

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class VideoFile(
    @Optional val collection: String,
    @Optional val id: Int,
    @Optional val image: String,
    @Optional val mission: String,
    @Optional val name: String,
    @Optional val news_name: String
)

@Serializable
data class Html5Video(
    @Optional val poster_url: String,
    @Optional val video_url: String
)

@Serializable
data class VideoFileX(
    @Optional val file_size: Int,
    @Optional val file_url: String,
    @Optional val format: String,
    @Optional val frame_rate: String,
    @Optional val height: Int,
    @Optional val width: Int
)

@Serializable
data class VideoModels(
    @Optional val collection: String,
    @Optional val credits: String,
    @Optional val html_5_video: Html5Video,
    @Optional val image: String,
    @Optional val image_retina: String,
    @Optional val mission: String,
    @Optional val name: String,
    @Optional val news_name: String,
    @Optional val short_description: String,
    @Optional val video_files: List<VideoFileX>
)