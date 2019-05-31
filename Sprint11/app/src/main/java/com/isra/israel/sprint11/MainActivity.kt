package com.isra.israel.sprint11

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val videosJob = Job()
    private val videosScope = CoroutineScope(Dispatchers.IO + videosJob)

    private lateinit var videoResultListAdapter: VideoResultListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        a_main_r_video_result_list.setHasFixedSize(true)
        a_main_r_video_result_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        videoResultListAdapter = VideoResultListAdapter()
        a_main_r_video_result_list.adapter = videoResultListAdapter

        videosScope.launch {
            val videos = SpaceTelescopeApiDao.getVideos(null)

            withContext(Dispatchers.Main) {
                videoResultListAdapter.setVideoList(videos)
            }
        }
    }
}
