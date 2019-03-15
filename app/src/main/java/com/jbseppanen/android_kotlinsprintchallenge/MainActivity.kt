package com.jbseppanen.android_kotlinsprintchallenge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    companion object {
        const val videoUrl = "https://media.stsci.edu/uploads/video_file/video_attachment/4976/STScI-H-v1845a-640x360.mp4"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
