package com.isra.israel.sprint11

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class VideoFile(@SerialName("file_url") var fileUrl: String?, @SerialName("file_size") var fileSize: Int?)