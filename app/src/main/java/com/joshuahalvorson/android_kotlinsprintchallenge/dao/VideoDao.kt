package com.joshuahalvorson.android_kotlinsprintchallenge.dao

import android.graphics.Bitmap
import android.support.annotation.WorkerThread
import com.joshuahalvorson.android_kotlinsprintchallenge.model.Video
import com.joshuahalvorson.android_kotlinsprintchallenge.model.VideoDetails
import com.joshuahalvorson.android_kotlinsprintchallenge.model.VideosList
import com.joshuahalvorson.android_kotlinsprintchallenge.network.NetworkAdapter
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

object VideoDao{
    const val VIDEOS_URL = "http://hubblesite.org/api/v3/videos"
    const val DETAIL_VIDEO_URL = "http://hubblesite.org/api/v3/video/"

    @WorkerThread
    suspend fun getVideos(): List<Video>{
        val (success, result) = NetworkAdapter.httpGetRequest(VIDEOS_URL)
        var videoList: List<Video>? = null
        if(success){
            videoList = Json.parse(Video.serializer().list, result)
        }
        return videoList?: listOf()
    }
}