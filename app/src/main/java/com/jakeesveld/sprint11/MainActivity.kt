package com.jakeesveld.sprint11

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    companion object{
        const val VIDEO_URL_BASE = "http://hubblesite.org/api/v3/video"
        const val VIDEO_LIST_URL = VIDEO_URL_BASE + "s"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
