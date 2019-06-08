package com.shoon.android_kotlinsprintchallenge

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class VideoListList(
    val error: String?,
    val limit: Int?,
    val offset: Int?,
    val number_of_page_results: Int?,
    val number_of_total_results: Int?,
    val status_code: Int?,
    val results: List<VideosList>?,
    val version: String?
)

@Serializable
data class VideosList(
    val id: Number?,
    val name: String?,
    val image: String?
)



@Serializable
data class VideoURL(
    val name: String?,
    val short_description: String?,
    val youtube_id: String?,
    val credits: String?,
    val news_name: String?,
    val mission: String?,
    val collection: String?,
    val image: String?,
    val image_retina: String?,
    val html_5_video: Html_5_video?,
    val video_files: List<Video_Files>?
)

@Serializable
data class Html_5_video(
    val video_url: String?,
    val poster_url: String?
)

@Serializable
data class Video_Files(
    val file_url: String?,
    val file_size: Number?,
    val width: Number?,
    val height: Number?,
    val frame_rate: String?,
    val format: String?
)
