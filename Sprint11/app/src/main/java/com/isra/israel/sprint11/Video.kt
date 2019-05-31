package com.isra.israel.sprint11

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Video(
    var name: String?,
    @SerialName("video_files") var videoFiles: MutableList<VideoFile>?
) {
    companion object {
        const val Q_1080p = 0
        const val Q_720p = 1
    }
}

fun Video.getVideoUrl_720p() : String? {
    if (videoFiles == null) {
        return null
    }

    return videoFiles!![Video.Q_720p].fileUrl
}