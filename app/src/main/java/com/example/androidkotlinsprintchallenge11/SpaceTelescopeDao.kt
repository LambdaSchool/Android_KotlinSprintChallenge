package com.example.androidkotlinsprintchallenge11

import android.support.annotation.WorkerThread
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import java.net.URL
import java.util.concurrent.ThreadPoolExecutor

const val VIDEO_URL = "http://hubblesite.org/api/v3/videos/all"
const val VIDEO_SINGLE_URL = "http://hubblesite.org/api/v3/video/"
const val VIDEO_INDEX = 3
var videoModels: VideoModels? = null
var videoFile: VideoFile? = null

object SpaceTelescopeDao {

    suspend fun getVideo(scheduler: ThreadPoolExecutor): String = coroutineScope {
        withContext(scheduler.asCoroutineDispatcher()) {

            getVideoFile()
            getVideoModel(videoFile!!.id)

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
            }
        })
    }



}
