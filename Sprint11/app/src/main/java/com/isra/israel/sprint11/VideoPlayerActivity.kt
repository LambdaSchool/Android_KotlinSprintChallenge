package com.isra.israel.sprint11

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_video_player.*
import kotlinx.coroutines.*
import java.util.*

class VideoPlayerActivity : AppCompatActivity() {

    private val videoFileJob = Job()
    private val videoFileScope = CoroutineScope(Dispatchers.IO + videoFileJob)

    private val seekBarUpdateTimer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        if (intent == null) {
            return
        }

        val videoId = intent.getIntExtra("video_id", -1)

        videoFileScope.launch {
            val video = SpaceTelescopeApiDao.getVideo(videoId)
            if (video != null) {
                val videoUrl = video.getVideoUrl_720p()
                if (videoUrl != null) {
                    withContext(Dispatchers.Main) {
                        a_video_player_vv.setVideoURI(Uri.parse(videoUrl))
                    }
                }
            }
        }

        a_video_player_b_play_pause.isEnabled = false
//        a_video_player_sb_buffer.setOnTouchListener(object: View.OnTouchListener {
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                return true
//            }
//        })

        a_video_player_sb_buffer.isEnabled = false

        a_video_player_vv.setOnPreparedListener {
            a_video_player_preparing_pb.visibility = View.GONE
            a_video_player_b_play_pause.isEnabled = true
            a_video_player_b_play_pause.setOnClickListener {
                if (a_video_player_vv.isPlaying) {
                    a_video_player_vv.pause()
                    a_video_player_b_play_pause.text = "play"
                } else {
                    a_video_player_vv.start()
                    a_video_player_b_play_pause.text = "pause"
                }

            }

            a_video_player_sb_current.max = a_video_player_vv.duration
            a_video_player_sb_current.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        a_video_player_vv.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })

            seekBarUpdateTimer.scheduleAtFixedRate(object: TimerTask() {
                override fun run() {
                    runOnUiThread {
                        a_video_player_sb_current.progress = a_video_player_vv.currentPosition
                        a_video_player_sb_buffer.progress = a_video_player_vv.bufferPercentage
                    }
                }

            } ,0, 1000)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        seekBarUpdateTimer.cancel()
        videoFileScope.cancel()
    }
}
