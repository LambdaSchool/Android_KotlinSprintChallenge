package com.example.androidkotlinsprintchallenge11

data class VideoFile(
    val file_size: Int,
    val file_url: String,
    val format: String,
    val frame_rate: String,
    val height: Int,
    val width: Int
)