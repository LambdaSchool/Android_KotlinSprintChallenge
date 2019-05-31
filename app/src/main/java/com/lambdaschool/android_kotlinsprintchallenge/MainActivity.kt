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
                video_view.setVideoPath("https://media.stsci.edu/uploads/video_file/video_attachment/4532/STScI-H-v1737a-640x360.mp4")
                mediaController?.setAnchorView(video_view)
                video_view.setMediaController(mediaController)
                video_view.start()
            }
        }


    }
}
