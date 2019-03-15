package com.joshuahalvorson.android_kotlinsprintchallenge

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.SeekBar
import android.widget.VideoView
import com.joshuahalvorson.android_kotlinsprintchallenge.dao.VideoDao
import com.joshuahalvorson.android_kotlinsprintchallenge.model.Video
import com.joshuahalvorson.android_kotlinsprintchallenge.model.VideoDetails
import com.joshuahalvorson.android_kotlinsprintchallenge.model.getVideoUrl
import kotlinx.coroutines.*
import kotlinx.coroutines.Runnable

class MainActivity : AppCompatActivity() {

    private val dataJob = Job()
    private val dataScope = CoroutineScope(Dispatchers.IO + dataJob)

    var videos = mutableListOf<Video>()
    var video = VideoDetails()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.video_button)
        val seekbar = findViewById<SeekBar>(R.id.video_seek_bar)
        val videoView = findViewById<VideoView>(R.id.object_video_view)

        button.text = "Pause"
        button.setOnClickListener {
            val isPlaying = videoView.isPlaying
            button.text = if (isPlaying) "Play" else "Pause"
            if (isPlaying) {
                videoView.pause()
            } else {
                videoView.start()
            }
        }

        dataScope.launch {
            videos.addAll(VideoDao.getVideos())
            video = VideoDao.getVideoDetail(videos[0].id)
            withContext(Dispatchers.Main) {
                val url = video.getVideoUrl()
                videoView.setVideoURI(Uri.parse(url?.file_url))
                videoView.start()
                seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onStopTrackingTouch(seekBar: SeekBar) {
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar) {
                    }

                    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                        if (fromUser) {
                            videoView.seekTo(progress * 1000)
                        }
                    }
                })
            }
        }
    }

}
