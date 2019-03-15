package com.jbseppanen.android_kotlinsprintchallenge

import android.content.Context
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json


class MainActivity : AppCompatActivity() {

    companion object {
        const val url =
//                "https://media.stsci.edu/uploads/video_file/video_attachment/4976/STScI-H-v1845a-640x360.mp4"
            "http://hubblesite.org/api/v3/video/1097"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context: Context = this

        val dataJob = Job()
        val dataScope = CoroutineScope(Dispatchers.IO + dataJob)
        dataScope.launch {

            val (success, result) = NetworkAdapter.httpRequest(
                url, NetworkAdapter.GET, NetworkAdapter.RESPONSE_STRING, null, null
            )
            if (success) {
                val videoData = Json.nonstrict.parse(VideoData.serializer(), result)
                val videoFile = videoData.video_files[videoData.video_files.size - 1]
                val uri = Uri.parse(videoFile.file_url)
                withContext(Dispatchers.Main) {
                    player_video.setVideoURI(uri)

//                    play_pauseVideo()
                }
            }
        }

        player_button_play.setOnClickListener {
            play_pauseVideo()
        }

        player_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    player_video.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
    }

    fun play_pauseVideo() {
        player_seekbar.max = player_video.duration
        val buttonDrawable:Drawable?
        if (player_video.isPlaying) {
            player_video.pause()
            buttonDrawable = getDrawable(R.drawable.avd_anim_stop_to_play)
        } else {
            player_video.start()
            Thread(Runnable {
                while (player_video.isPlaying) {
                    runOnUiThread { player_seekbar.progress = player_video.currentPosition }
                    Thread.sleep(100)
                }
            }).start()
            buttonDrawable = getDrawable(R.drawable.avd_anim_play_to_stop)
        }
        player_button_play.setImageDrawable(buttonDrawable)
        if (buttonDrawable is Animatable) {
            (buttonDrawable as Animatable).start()
        }
    }
}
