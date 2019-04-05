package com.example.android_kotlinsprintchallenge.dao


import android.support.annotation.WorkerThread
import com.example.android_kotlinsprintchallenge.model.Video
import kotlinx.serialization.json.Json
import kotlinx.serialization.list


object VideoDAO {

    const val PAGE_URL = "http://hubblesite.org/api/v3/video/"


    


    @WorkerThread
    suspend fun getVideos(): List<Video> {
        val (success, result) = NetworkAdapter.httpGetRequest(PAGE_URL)
        var videoList: List<Video>? = null
        if(success) {
            videoList = Json.parse(Video.serializer().list, result)
        }
        return videoList?: listOf()
    }






}



