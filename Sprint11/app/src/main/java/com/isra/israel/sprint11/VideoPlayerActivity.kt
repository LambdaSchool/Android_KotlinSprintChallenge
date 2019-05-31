package com.isra.israel.sprint11

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_video_player.*
import kotlinx.coroutines.*
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter

class VideoPlayerActivity : AppCompatActivity() {

    private val videoFileJob = Job()
    private val videoFileScope = CoroutineScope(Dispatchers.IO + videoFileJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        if (intent == null) {
            return
        }

        val videoId = intent.getIntExtra("video_id", -1)

        videoFileScope.launch {
            val video = SpaceTelescopeApiDao.getVideo(videoId)
            if (video != null && video.videoFiles?.size != 0) {
//                    val videoFile = SpaceTelescopeApiDao.getVideoFile(video.videoFiles?.get(0)!!.fileUrl!!)
//                    val file = File(cacheDir.path + "/" + videoId + ".vc")
//                    if (!file.exists()) {
//                        val fileIS = FileInputStream(file)
//
//                        val fileWriter = FileWriter(fileIS)
//
//                        val file
//                    }
//
//                    val videoUri = Uri.parse(file.path)

                withContext(Dispatchers.Main) {
                    val str = video.videoFiles!!.get(0)!!.fileUrl
                    //a_video_player_vv.setVideoPath(video.videoFiles!!.get(0)!!.fileUrl)
                    a_video_player_vv.setVideoURI(Uri.parse(video.videoFiles!!.get(0)!!.fileUrl))
                    a_video_player_vv.start()

                    Toast.makeText(
                        this@VideoPlayerActivity,
                        "Video file for ${video.name} downloaded",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        a_video_player_vv.setOnPreparedListener {
            it.start()
        }

    }
}
