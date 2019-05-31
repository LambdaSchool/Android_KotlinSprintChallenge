package com.vivekvishwanath.android_kotlinsprintchallenge

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = this

        val viewManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }
        val viewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)
        viewModel.getVideos().observe(this, Observer {videos ->
            findViewById<RecyclerView>(R.id.recycler_view).adapter = videos?.let { VideoListAdapter(it, context) }
        })
    }
}
