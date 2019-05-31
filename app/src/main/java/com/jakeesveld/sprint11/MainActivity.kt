package com.jakeesveld.sprint11

import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {
    val dataJob = Job()
    val dataScope = CoroutineScope(Dispatchers.Main + dataJob)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoView: VideoView = video_view
        val playPauseButton: Button = play_pause_button
        playPauseButton.isEnabled = false
        val progressSeekBar: SeekBar = progress_seek_bar


        val videoProgressListenerRunnable: Runnable = Runnable {
            try {
                Thread.sleep(1000)
            }catch (e: InterruptedException){
                e.printStackTrace()
            }
            while (videoView.isPlaying){
                runOnUiThread(Runnable {
                    progressSeekBar.progress = videoView.currentPosition
                })
                try {
                    Thread.sleep((videoView.duration / videoView.width).toLong())
                }catch (e: InterruptedException){
                    e.printStackTrace()
                }
            }
        }

        playPauseButton.setOnClickListener {
            if (videoView.isPlaying){
                videoView.pause()
                playPauseButton.text = "Play"

            }else{
                videoView.start()
                playPauseButton.text = "Pause"
                Thread(videoProgressListenerRunnable).start()
            }
        }

        videoView.setOnPreparedListener { playPauseButton.isEnabled = true }


        dataScope.launch {
            var resultString = ""
            withContext(Dispatchers.IO){
                resultString = NetworkAdapter.httpGetRequest(VideoItem.Companion.VIDEO_URL)
            }
            val videoFile = Json.nonstrict.parse(VideoItem.serializer(), resultString)

            videoView.setVideoURI(Uri.parse(videoFile.html_5_video?.video_url))
            progressSeekBar.max = videoView.duration
            progressSeekBar.progress = 0
        }
    }


}

