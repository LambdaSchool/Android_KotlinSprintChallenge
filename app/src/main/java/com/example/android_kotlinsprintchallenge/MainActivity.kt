package com.example.android_kotlinsprintchallenge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.SeekBar
import com.example.android_kotlinsprintchallenge.VideoData.Companion.url
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        player_button_play.isEnabled = false
        val dataJob = Job()
        val dataScope = CoroutineScope(Dispatchers.IO + dataJob)
        dataScope.launch {

            val (success, result) = NetworkAdapter.httpRequest(
                url, NetworkAdapter.GET, null
            )
            if (success) {
                //getting all videos
                val videoData = Json.nonstrict.parse(VideoData.serializer(), result)
                //getting a specific video
                val uri = Uri.parse(videoData.getLastVideoUrl())
                withContext(Dispatchers.Main) {
                    player_video.setVideoURI(uri)
                    player_video.setOnPreparedListener {
                        player_seekbar.max = player_video.duration
                        play_pauseVideo()
                        player_button_play.isEnabled = true
                        Thread(Runnable {
                            while (player_video.bufferPercentage < 100) {
                                runOnUiThread { player_seekbar.secondaryProgress = (player_video.bufferPercentage * player_video.duration) / 100
                                }
                            }
                            play_pauseVideo()
                        }).start()
                    }
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

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    fun play_pauseVideo() {
        val buttonDrawable: Drawable?
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
