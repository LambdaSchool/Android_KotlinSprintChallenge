package com.example.androidkotlinsprintchallenge11

import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

const val VIDEO_URL = "http://hubblesite.org/api/v3/videos/all"
const val VIDEO_SINGLE_URL = "http://hubblesite.org/api/v3/video/"
const val VIDEO_INDEX = 3
var videoModels: VideoModels? = null
var videoFile: VideoFile? = null

object SpaceTelescopeDao {

    suspend fun getVideo(): String = coroutineScope {

        getVideoFile()

        //TODO: Figure out a way to fix this disgusting code
         Thread.sleep(5000)

        withContext(Dispatchers.Main) {
            videoModels!!.html_5_video.video_url
        }
    }



    fun getVideoModel(id:Int) {
        NetworkAdapter.httpGetRequest(
            "$VIDEO_SINGLE_URL$id",
            object : NetworkAdapter.NetworkCallback {
                override fun returnResult(success: Boolean?, result: String) {
                    videoModels = Json.nonstrict.parse(VideoModels.serializer(), result)
                }
            })
    }


    fun getVideoFile() {

         NetworkAdapter.httpGetRequest("$VIDEO_URL", object : NetworkAdapter.NetworkCallback {
            override fun returnResult(success: Boolean?, result: String) {
                videoFile = Json.nonstrict.parse(VideoFile.serializer().list, result)[VIDEO_INDEX]
                getVideoModel(videoFile!!.id)
            }
        })
    }



}
