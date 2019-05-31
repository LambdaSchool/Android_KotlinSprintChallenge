package com.example.androidkotlinsprintchallenge11

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val threadPool = ThreadPoolExecutor(3, 3, 5L, TimeUnit.SECONDS, LinkedBlockingQueue())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnLoad = findViewById<Button>(R.id.btn_load_video)
        val btnPlayPause = findViewById<Button>(R.id.btn_play_pause)
        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val vvVideoView = findViewById<VideoView>(R.id.vv_video_view)

        val mediaController = MediaController(this)
        vvVideoView.setMediaController(mediaController)

        btnLoad.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) { vvVideoView.setVideoPath(SpaceTelescopeDao.getVideo(threadPool)) }
        }

        btnPlayPause.setOnClickListener {  }
    }

}


