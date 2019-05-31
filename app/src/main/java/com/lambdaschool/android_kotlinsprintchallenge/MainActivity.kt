package com.lambdaschool.android_kotlinsprintchallenge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var videoModel: VideoModel? = null
        val job = Job()
        val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main + job)

        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                videoModel = HubbleVideoDao.retrieveHubbleVideoDataById("1210")
            }
            withContext(Dispatchers.Main) {
                val mediaController: MediaController? = MediaController(this@MainActivity)
                video_view.setVideoPath(videoModel?.video_files?.get(1)?.file_url)
                mediaController?.setAnchorView(video_view)
                video_view.setMediaController(mediaController)
                video_view.start()
            }
        }


    }
}
