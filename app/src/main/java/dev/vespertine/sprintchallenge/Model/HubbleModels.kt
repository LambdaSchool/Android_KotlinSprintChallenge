package dev.vespertine.sprintchallenge.Model

import kotlinx.serialization.Optional

data class HubbleNewsPage(
    val name: String? = "",
    val news_id: String? = "",
    val url: String
)

//news_id will be used to pull specific stories.
data class HubbleNewsSpecific(
    val name: String? = "",
    val url: String? = "",
    val abstract: String? = "",
    val thumbnail: String? = "",
    val release_videos: List<Int>? = listOf(),
    @Optional
    val credits: String? = "",
    @Optional
    val keystone_image_1x: String? = "",
    @Optional
    val keystone_image_2x: String? = "",
    @Optional
    val mission: String? = "",
    @Optional
    val news_id: String? = "",
    @Optional
    val publication: String? = "",
    @Optional
    val release_images: List<Int>,
    @Optional
    val thumbnail_1x: String? = "",
    @Optional
    val thumbnail_2x: String? = "",
    @Optional
    val thumbnail_retina: String? = ""

)


