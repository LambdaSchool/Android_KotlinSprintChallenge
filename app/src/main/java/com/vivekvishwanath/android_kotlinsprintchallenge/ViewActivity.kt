package com.vivekvishwanath.android_kotlinsprintchallenge

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.VideoView
import kotlinx.coroutines.*

class ViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val progressJob = Job()
        val bufferJob = Job()
        val mainJob = Job()
        val progressListenerScope = CoroutineScope(Dispatchers.IO + progressJob)
        val bufferScope = CoroutineScope(Dispatchers.IO + bufferJob)
        val mainScope = CoroutineScope(Dispatchers.Main + mainJob)

        val videoView = findViewById<VideoView>(R.id.video_view)
        val controlButton = findViewById<Button>(R.id.play_pause_button)
        val seekbar = findViewById<SeekBar>(R.id.seekbar)
        val video = intent.getSerializableExtra("video") as Video

        videoView.setVideoURI(Uri.parse(video.getSmallestVideo()))
        videoView.setOnPreparedListener{
            seekbar.max = videoView.duration
            videoView.pause()
            bufferScope.launch {
                while (videoView.bufferPercentage < 100) {
                    mainScope.launch {
                        seekbar.secondaryProgress = (videoView.bufferPercentage * seekbar.max) / 100
                    }
                }
                delay((videoView.duration / 1000).toLong())
            }
        }

        controlButton.setOnClickListener{
            if (videoView.isPlaying) {
                videoView.pause()
                controlButton.text = "Play"
            } else {
                videoView.start()
                controlButton.text = "Pause"
                progressListenerScope.launch {
                    while(videoView.isPlaying) {
                        val currentPosition = videoView.currentPosition
                        mainScope.launch {
                            seekbar.progress = currentPosition
                        }
                        delay((videoView.duration / 1000).toLong())
                    }
                }
                videoView.start()
            }
        }
        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.progress?.let { videoView.seekTo(it) }
            }
        })
    }
}
