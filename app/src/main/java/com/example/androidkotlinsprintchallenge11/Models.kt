package com.example.androidkotlinsprintchallenge11

import kotlinx.serialization.Serializable

@Serializable
data class Model(
    val collection: String,
    val credits: String,
    val html_5_video: Html5Video,
    val image: String,
    val image_retina: String,
    val mission: String,
    val name: String,
    val news_name: String,
    val short_description: String,
    val video_files: List<VideoFile>
)

@Serializable
data class VideoFile(
    val file_size: Int,
    val file_url: String,
    val format: String,
    val frame_rate: String,
    val height: Int,
    val width: Int
)