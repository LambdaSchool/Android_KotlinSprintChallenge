package com.jakeesveld.sprint11

import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {
    companion object{
        const val VIDEO_URL_BASE = "http://hubblesite.org/api/v3/video"
        const val VIDEO_LIST_URL = VIDEO_URL_BASE + "s"
    }
    val dataJob = Job()
    val dataScope = CoroutineScope(Dispatchers.Main + dataJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val videoView: VideoView = video_view

        dataScope.launch {
            var resultString = ""
            withContext(Dispatchers.IO){
                resultString = NetworkAdapter.httpGetRequest("$VIDEO_URL_BASE/1205")
            }
            val videoFile = Json.nonstrict.parse(VideoItem.serializer(), resultString)

            videoView.setVideoURI(Uri.parse(videoFile.html_5_video?.video_url))
            videoView.start()
        }
    }
}
