package com.spencerstock.android_kotlinsprintchallenge

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataJob = Job()
        val dataScope = CoroutineScope(Dispatchers.IO + dataJob)
        dataScope.launch {

            val (success, result) = NetworkAdapter.httpRequest(VideoData.VIDEO_URL, NetworkAdapter.GET, null)
            if (success) {
                val videoData = Json.nonstrict.parse(VideoData.serializer(), result)
                val uri = Uri.parse(videoData.getUrl())
                withContext(Dispatchers.Main) {
                    video_view.setVideoURI(uri)

                    video_view.setOnPreparedListener {
                        seek_bar.max = video_view.duration
                        video_view.start()
                        Thread(Runnable {
                            while (video_view.bufferPercentage < 100) {
                                Thread.sleep(200)
                                runOnUiThread {
                                    seek_bar.secondaryProgress =
                                        (video_view.bufferPercentage * video_view.duration) / 100
                                    seek_bar.progress = (video_view.currentPosition)
                                }
                            }
                        }).start()
                    }
                }
            }
        }


        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    video_view.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        button_play.setOnClickListener {
            if (video_view.isPlaying) {
                video_view.pause()
            } else video_view.start()
        }
    }
}