package com.isra.israel.sprint11

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Video(
    var name: String?,
    @SerialName("youtube_id") var youtubeId: String?,
    @SerialName("video_files") var videoFiles: MutableList<VideoFile>?
)