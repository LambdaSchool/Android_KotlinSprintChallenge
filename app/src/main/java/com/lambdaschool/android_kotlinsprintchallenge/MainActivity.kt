package com.lambdaschool.android_kotlinsprintchallenge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.UiThread
import android.widget.MediaController
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.button_retrieve.setOnClickListener { showVideo(edit_text_retrieve.text.toString()) }

        this.seek_bar!!.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        this.video_view.seekTo(progress)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        seekBar?.progress?.let { this.video_view.seekTo(it) }
    }

    fun showVideo(videoId: String) {
        var videoModel: VideoModel? = null
        val job = Job()
        val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main + job)

        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                videoModel = HubbleVideoDao.retrieveHubbleVideoDataById(videoId)
            }
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity,videoModel?.short_description,Toast.LENGTH_LONG).show()
                val mediaController: MediaController? = MediaController(this@MainActivity)
                video_view.setVideoPath(videoModel?.getVideoUrl())
                mediaController?.setAnchorView(video_view)
                video_view.setMediaController(mediaController)
                video_view.setOnPreparedListener {
                    seek_bar.max = video_view.duration
                    seek_bar.progress = 0
                    video_view.start()
                }
            }
        }
    }
}