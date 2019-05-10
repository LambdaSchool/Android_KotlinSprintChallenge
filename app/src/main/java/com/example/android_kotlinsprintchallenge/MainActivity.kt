package com.example.android_kotlinsprintchallenge

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.VideoView
import com.example.android_kotlinsprintchallenge.model.Video
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {

    companion object {
        const val url = "http://hubblesite.org/api/v3/video/1200"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonNext = findViewById<Button>(R.id.next_button)
        val buttonPause = findViewById<Button>(R.id.pause_button)
        val buttonPrevious = findViewById<Button>(R.id.previous_button)
        val seekbar = findViewById<SeekBar>(R.id.seekBar)
        val videoView = findViewById<VideoView>(R.id.player_video)

        val dataJob = Job()
        val dataScope = CoroutineScope(Dispatchers.IO + dataJob)

        suspend fun getVideos() {
            val (fileSuccess, fileResult) = NetworkAdapter.httpGetRequest(
                "http://hubblesite.org/api/v3/video/1200"
            )
            if (fileSuccess) {
                val video = Json.nonstrict.parse(Video.serializer(), fileResult)
                withContext(Dispatchers.Main) {
                    player_video.setVideoURI(Uri.parse(video.getVideoFileUrl()))
                }
            }
        }

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    player_video.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        buttonPause.setOnClickListener {
            val isPlaying = videoView.isPlaying
            if (isPlaying) {
                videoView.pause()
            } else {
                videoView.start()
            }
        }


    }
}


/**
 *     @WorkerThread
suspend fun getVideos(): List<Video> {
val (success, result) = NetworkAdapter.httpGetRequest(PAGE_URL)
var videoList: List<Video>? = null
if(success) {
videoList = Json.parse(Video.serializer().list, result)
}
return videoList?: listOf()
}
 */


