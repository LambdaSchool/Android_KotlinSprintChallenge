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
    val id: Int?,
    val name: String?,
    val image: String?
)



@Serializable
data class VideoURL @JvmOverloads constructor(
    val name: String?,
    val video_files: List<Video_Files>?=null
)



@Serializable
data class Video_Files(
    val file_url: String?

)
